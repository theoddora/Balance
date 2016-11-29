package com.balance;

import com.balance.controller.UserController;
import com.balance.dao.ProductDao;
import com.balance.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by pgenev on 29/11/2016.
 */
@WebAppConfiguration()
@ContextConfiguration(locations = "classpath:context.xml")
@RunWith(SpringJUnit4ClassRunner.class)

public class ProductsTest {
    @Autowired
    private ProductDao dao;

    @Test
    public void listAllFruits() throws Exception {
        dao.getAllFruits();
        for(Product product : dao.getAllFruits()){
            System.out.println(product);
        }
    }

    @Test
    public void listAllVegetables() throws Exception {
        dao.getAllVegetables();
        for(Product product : dao.getAllVegetables()){
            System.out.println(product);
        }
    }

}

