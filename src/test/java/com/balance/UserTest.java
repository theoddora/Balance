package com.balance;

import com.balance.config.SpringWebConfig;
import com.balance.dao.UserDAO;
import com.balance.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by pgenev on 23/11/2016.
 */




@WebAppConfiguration()
@ContextConfiguration(locations = "classpath:context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserTest {

    @Autowired
    private UserDAO dao;

    @Test
    public void testUserCreateStatement() throws Exception {
        dao.createUser(new User("petar1", "petar@abv.bg", "petar", "petar123"));
    }

    @Test
    public void testListAllUsers(){
        dao.listUsers();
    }

}
