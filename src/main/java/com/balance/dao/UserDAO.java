package com.balance.dao;

import com.balance.exceptions.NoSuchUserException;
import com.balance.exceptions.PasswordsDontMatchException;
import com.balance.model.User;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by pgenev on 22/11/2016.
 */
public interface UserDAO {

    void setDataSource(DataSource ds);

    void createUser(User user);

    List<User> listUsers();

    void delete(String email);

    User findByUserEmail(String email) throws IncorrectResultSizeDataAccessException;

    User findByUsername(String username) throws IncorrectResultSizeDataAccessException;

    User getUser(String username, String password) throws NoSuchUserException, PasswordsDontMatchException;
}
