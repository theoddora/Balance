package com.balance.controller;

import com.balance.dao.ProductDao;
import com.balance.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/product/buy?productId = {id}+amount={amount}")
    public String addToCart(@PathVariable("id") int id, @PathVariable("amount") double amount, Model model, @ModelAttribute("cart") Map<Integer, Double> cart) {
        if (cart == null) {
            cart = new HashMap<>();
        }
        cart.put(id, amount);

        model.addAttribute("cart", cart);
        return "work";
    }

}
