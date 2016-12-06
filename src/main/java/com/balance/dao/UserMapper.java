package com.balance.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.balance.model.User;

/**
 * Created by pgenev on 23/11/2016.
 */
public class UserMapper implements RowMapper<User> {

    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setName(rs.getString("name"));
        user.setUsername(rs.getString("username"));
        user.setId(rs.getInt("user_id"));
        user.setIsAdmin(rs.getBoolean("is_admin"));
        return user;
    }
}
