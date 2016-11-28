package com.balance.dao;

import com.balance.model.User;

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

    User getUser(String email);

   // boolean isValidUser(String username, String password);

}
