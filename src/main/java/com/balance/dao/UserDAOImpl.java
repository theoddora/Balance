package com.balance.dao;

import com.balance.exceptions.NoSuchUserException;
import com.balance.exceptions.PasswordsDontMatchException;
import com.balance.exceptions.UserAlreadyExistsException;
import com.balance.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import sun.security.util.Password;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by pgenev on 22/11/2016.
 */
@Component
public class UserDAOImpl implements UserDAO {

    private DataSource dataSource;
    private NamedParameterJdbcTemplate jdbcTemplateObject;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private static final String WRONG_PASSWORD = "You have entered a wrong password";
    private static final String WRONG_USERNAME = "You have entered a wrong username";
    private static final String DUPLICATE_USERNAME = "User with that username/email already exists.";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void createUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());

        String SQL = "INSERT INTO \"user\" (username, email, \"password\", \"name\") " +
                "values (:username, :email, :password, :name)";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", user.getName());
        params.put("username", user.getUsername());
        params.put("email", user.getEmail());
        params.put("password", hashedPassword);
        try {
            jdbcTemplateObject.update(SQL, params);
        } catch (DuplicateKeyException e) {
            throw new UserAlreadyExistsException(DUPLICATE_USERNAME);
        }
    }

    @Override
    public List<User> listUsers() {
        String SQL = "SELECT * FROM \"user\" ";
        List<User> users = jdbcTemplateObject.query(SQL, new UserMapper());
        return users;
    }

    @Override
    public void delete(String email) {

    }

    @Override
    public User findByUserEmail(String email) throws IncorrectResultSizeDataAccessException {
        String sql = "select * from \"user\" where email like :email ";
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        User user = jdbcTemplateObject.queryForObject(sql, params, new UserMapper());
        return user;
    }

    @Override
    public User findByUsername(String username) throws IncorrectResultSizeDataAccessException {
        String sql = "select * from \"user\" where username like :username ";
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        User user = jdbcTemplateObject.queryForObject(sql, params, new UserMapper());
        return user;
    }

    @Override
    public User getUser(String username, String password) throws IncorrectResultSizeDataAccessException, PasswordsDontMatchException {
        User user = null;
        try {
            user = findByUsername(username);
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new NoSuchUserException(WRONG_USERNAME);
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordsDontMatchException(WRONG_PASSWORD);
        }
        return user;
    }
}
