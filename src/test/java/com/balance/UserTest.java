package com.balance;

import com.balance.controller.UserController;
import com.balance.dao.UserDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by pgenev on 23/11/2016.
 */




@WebAppConfiguration()
@ContextConfiguration(locations = "classpath:context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserTest {

    @Autowired
    private UserDAO dao;

    /*
    @Test
    public void testUserCreateStatement() throws Exception {
        dao.createUser(new User("theoddora", "theoddora@abv.bg", "theodora", "tedi123"));
    }
    */


    @Test
    public void shouldShowRegistration() throws Exception {
        UserController controller = new UserController();
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/registration")).andExpect(view().name("register"));
    }

    @Test
    public void testListAllUsers(){
        dao.listUsers();
    }

    @Test
    public void testDao(){
        Assert.notNull(dao);
    }


}
