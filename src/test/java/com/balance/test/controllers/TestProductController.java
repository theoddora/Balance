package com.balance.test.controllers;

import com.balance.config.SpringWebConfig;
import com.balance.controller.ProductController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by hangelov on 24/11/2016.
 */
@WebAppConfiguration
@ContextConfiguration(classes = SpringWebConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestProductController {


    @Test
    public void testProductController() throws Exception{
        ProductController controller = new ProductController();
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/product")
                .header("host", "localhost:8080"))
                .andExpect(status().isOk());

    }

}
