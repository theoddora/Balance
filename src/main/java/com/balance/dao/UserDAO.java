package com.balance.dao;

import javax.sql.DataSource;

import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.balance.exceptions.NoSuchUserException;
import com.balance.exceptions.PasswordsDontMatchException;
import com.balance.model.User;

public interface UserDAO {

    void setDataSource(DataSource ds);

    void createUser(User user);

    void delete(String email);

    User findByUserEmail(String email) throws IncorrectResultSizeDataAccessException;

    User findByUsername(String username) throws IncorrectResultSizeDataAccessException;

    User getUser(String username, String password) throws NoSuchUserException, PasswordsDontMatchException;
}
