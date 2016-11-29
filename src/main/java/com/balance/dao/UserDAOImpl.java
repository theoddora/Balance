package com.balance.dao;

import com.balance.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    private static final String SQL_SELECT_USER = "SELECT user_id, email, password, name FROM balance.user WHERE username = ?";

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
        jdbcTemplateObject.update(SQL, params);
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
    public User getUser(String email) {
        return null;
    }

/*
    @Override
    public boolean isValidUser(String username, String password) {


        User user = new User(); // change this to getUserByUsername

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        String SQL = "SELECT COUNT(1) FROM balance.user WHERE username = ? AND password = ?";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);
        params.put("password", hashedPassword);
        ResultSet resultSet = jdbcTemplateObject.query(SQL, new UserMapper());
        return resultSet.next() && resultSet.getInt(1) > 0;
    }
*/
}
