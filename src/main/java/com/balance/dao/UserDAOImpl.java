package com.balance.dao;

import javax.sql.DataSource;

import com.balance.model.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by pgenev on 22/11/2016.
 */
public class UserDAOImpl implements UserDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void createUser(String userName, String email, String password, String name) {
        String SQL = "INSERT INTO user (username, email, password, name) " +
                "values (:username, :email, :password, :name)";

    }

    @Override
    public List<User> listUsers() {
        return null;
    }

    @Override
    public void delete(String email) {

    }

    @Override
    public User getUser(String email) {
        return null;
    }
}
