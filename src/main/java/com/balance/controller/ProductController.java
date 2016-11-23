package com.balance.controller;

import com.balance.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hangelov on 22/11/2016.
 */
@Controller
public class ProductController {


    @Autowired
    ProductDao productDao;


    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String getProducts(Model model){





        return "product";

    }

}
