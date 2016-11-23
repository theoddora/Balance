package com.balance.dao;

import com.balance.model.User;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by pgenev on 22/11/2016.
 */
public interface UserDAO {

    public void setDataSource(DataSource ds);

    public void createUser(User user);

    public List<User> listUsers();

    public void delete(String email);

    public User getUser(String email);


}
