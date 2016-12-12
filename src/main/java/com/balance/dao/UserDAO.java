package com.balance.dao;

import javax.sql.DataSource;

import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.balance.model.User;

public interface UserDAO {

    void setDataSource(DataSource ds);

    void createUser(User user);

    User findByUserEmail(String email) throws IncorrectResultSizeDataAccessException;

    User findByUsername(String username) throws IncorrectResultSizeDataAccessException;
}
