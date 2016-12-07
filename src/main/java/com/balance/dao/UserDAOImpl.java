package com.balance.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.balance.exceptions.EmailAlreadyExistsException;
import com.balance.exceptions.UsernameAlreadyExistsException;
import com.balance.mail.SendEmail;
import com.balance.model.User;

@Component
public class UserDAOImpl implements UserDAO {

    private NamedParameterJdbcTemplate jdbcTemplateObject;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    //columns
    private static final String COlUMN_EMAIL = "email";
    private static final String COlUMN_USERNAME = "username";

    //error messages
    private static final String DUPLICATE_USERNAME = "User with that username already exists.";
    private static final String DUPLICATE_EMAIL = "User with that email already exists.";

    //sql queries
    private static final String INSERT_USER = "INSERT INTO \"user\" (username, email, \"password\", \"name\") "
        + "values (?, ?, ?, ?)";
    private static final String COUNT_USER_BY_EMAIL = "SELECT COUNT(*) as users FROM \"user\" WHERE email = :email;";
    private static final String COUNT_USERS_BY_USERNAME = "SELECT COUNT(*) as users FROM \"user\" WHERE username = :username;";
    private static final String SELECT_USER_BY_EMAIL = "SELECT user_id, username, email, password, name, is_admin FROM \"user\" WHERE email = :email;";
    private static final String SELECT_USER_BY_USERNAME = "SELECT user_id, username, email, password, name, is_admin FROM \"user\" WHERE username = :username;";
    private static final String SELECT_UUID = "SELECT unique_user_id FROM \"user\" WHERE email LIKE :email;";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new NamedParameterJdbcTemplate(dataSource);
    }

    //register
    @Override
    public void createUser(User user) {

        logger.info("In createUser() method for user with username " + user.getUsername());
        if (exists(COUNT_USER_BY_EMAIL, COlUMN_EMAIL, user.getEmail())) {
            logger.error("Email already exists - " + user.getEmail());
            throw new EmailAlreadyExistsException(DUPLICATE_EMAIL);
        }
        if (exists(COUNT_USERS_BY_USERNAME, COlUMN_USERNAME, user.getUsername())) {
            logger.error("Username already exists - " + user.getUsername());
            throw new UsernameAlreadyExistsException(DUPLICATE_USERNAME);
        }

        String hashedPassword = passwordEncoder.encode(user.getPassword());

        jdbcTemplateObject.getJdbcOperations().update(INSERT_USER,
            user.getUsername(),
            user.getEmail(),
            hashedPassword,
            user.getName());

        SendEmail email = new SendEmail();
        email.setReceiverEmail(user.getEmail());
        email.setCodeVerification(getCodeVerification(user.getEmail()));
        email.start();
        logger.info("Finish createUser() method for user with username " + user.getUsername());
    }

    @Override
    public User findByUserEmail(String email) {

        Map<String, Object> params = new HashMap<>();
        params.put(COlUMN_EMAIL, email);
        return jdbcTemplateObject.queryForObject(SELECT_USER_BY_EMAIL, params, new UserMapper());
    }

    @Override
    public User findByUsername(String username) {

        Map<String, Object> params = new HashMap<>();
        params.put(COlUMN_USERNAME, username);
        return jdbcTemplateObject.queryForObject(SELECT_USER_BY_USERNAME, params, new UserMapper());
    }

    @Override
    public void delete(String email) {

    }

    private boolean exists(String sql, String column_name, String parameter) {
        Map<String, Object> params = new HashMap<>();
        params.put(column_name, parameter);
        int count = jdbcTemplateObject.queryForObject(sql, params, Integer.class);
        return count > 0;
    }

    private String getCodeVerification(String email) {

        Map<String, Object> params = new HashMap<>();
        params.put(COlUMN_EMAIL, email);
        return jdbcTemplateObject.queryForObject(SELECT_UUID, params, String.class);
    }
}
