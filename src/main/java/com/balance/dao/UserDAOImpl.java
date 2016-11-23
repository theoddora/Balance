package com.balance.dao;

import com.balance.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

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
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void createUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        String SQL = "INSERT INTO \"user\" (username, email, password, name) " +
                "values (:username, :email, :password, :name)";


        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("username", username);
        params.put("email", email);
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
}
