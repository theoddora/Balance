package com.balance.controller;

import com.balance.dao.ProductDao;
import com.balance.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by hangelov on 22/11/2016.
 */
@Controller
public class ProductController {


    @Autowired
    ProductDao productDao;




    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String getProducts(Model model) {

        List<Product> products = productDao.getAllProducts();
        model.addAttribute("products", products);

        return "work";

    }


    @RequestMapping(value = "/addproducts", method = RequestMethod.GET)
    public String addProducts(Model model) {
        model.addAttribute(new Product());


        return "addproducts";

    }


    @RequestMapping(value = "/addproducts", method = RequestMethod.POST)
    public String addGivenProduct(Product product) {
        System.out.println(product);
        productDao.insertProduct(product);


        return "/";

    }



}
