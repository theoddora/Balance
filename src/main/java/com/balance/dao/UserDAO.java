package com.balance.dao;

import com.balance.model.User;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by pgenev on 22/11/2016.
 */
public interface UserDAO {

    public void setDataSource(DataSource ds);

    public void createUser(String userName, String email, String password, String name);

    public List<User> listUsers();

    public void delete(String email);

    public User getUser(String email);


}
