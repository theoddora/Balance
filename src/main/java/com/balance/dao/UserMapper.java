package com.balance.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.balance.exceptions.UserException;
import com.balance.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

/**
 * Created by pgenev on 23/11/2016.
 */
public class UserMapper implements RowMapper<User> {

    private static final Logger logger = LoggerFactory.getLogger(UserMapper.class);

    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        try {
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setUsername(rs.getString("username"));
            user.setId(rs.getInt("user_id"));
        } catch (UserException e) {
            logger.error("User information exception", e);
            throw new SQLException(e);
        }

        return user;
    }
}
