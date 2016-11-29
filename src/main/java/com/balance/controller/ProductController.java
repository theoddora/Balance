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


    @RequestMapping(value = "/increasequantity", method = RequestMethod.GET)
    public String addProducts(Model model) {

        List<Product> fruits = productDao.getAllFruits();
        List<Product> vegetables = productDao.getAllVegetables();

        model.addAttribute("fruits", fruits);
        model.addAttribute("vegetables", vegetables);

        model.addAttribute(new Product());


        return "addproducts";

    }


    @RequestMapping(value = "/increasequantity", method = RequestMethod.POST)
    public String updateGivenProduct(Product product) {
        System.out.println(product);
        if(product.getAmountKilo() != 0) {
            productDao.increaseProductByKilo(product.getAmountKilo(), product.getId());
        }else if(product.getAmountPiece() != 0){
            productDao.increaseProductByPiece(product.getAmountPiece(), product.getId());
        }

        return "/";

    }


}
