package com.balance.test.dao;

import com.balance.dao.ProductDao;
import com.balance.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by hangelov on 24/11/2016.
 */
@WebAppConfiguration()
@ContextConfiguration(locations = "classpath:context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductDaoTest {

    @Autowired
    ProductDao productDao;





    @Test
    public void testFindProductById() throws  Exception{
        Assert.assertNotNull(productDao.findProductById(6));

    }

}
