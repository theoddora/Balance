package com.balance.controller;

import com.balance.dao.ProductDao;
import com.balance.model.Product;
import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
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


    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);


    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String getProducts(Model model) {

        List<Product> products = productDao.getAllProducts();
        model.addAttribute("products", products);

        return "work";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String addToCart(@RequestParam("productId") int id, @RequestParam("amount") double amount, Model model, Principal principal, HttpSession session) {

        if (principal == null) {
            return "log_in";
        }
        Map<Product, Double> cart = (Map<Product, Double>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<Product, Double>();
           session.setAttribute("cart", cart);
        }
        Product product = productDao.findProductById(id);

        Double totalPrice = 0.0;



        boolean isForKilo = product.getIsForKilo();
        String message = null;

        if (productDao.hasEnoughAmount(amount, id, isForKilo)) {
            synchronized (productDao) {
                if (isForKilo) {
                    productDao.decreaseProductByKilo(amount, id);
                } else {
                    productDao.decreaseProductByPiece( amount, id);
                }
            }
            cart.put(product, amount);
            message = "The selected amount has been added to the cart";


        } else {
            cart.put(product, 0.0);
            double currentAmount = productDao.getCurrentAmount(id, isForKilo);
            message = "There is not enough amount from " + product.getName() + ". The current amount is " + currentAmount + ".";
        }
        for (Product productInCart : cart.keySet()) {
            totalPrice += cart.get(productInCart)*(productInCart.getPrice()-productInCart.getPrice()*productInCart.getDiscount());
        }

        model.addAttribute("message", message);
        model.addAttribute("cart", cart);
        session.setAttribute("totalPrice", totalPrice);
        return "cart";


    }


    @RequestMapping(value = "/increasequantity", method = RequestMethod.GET)
    public String addProducts(Model model) {

        List<Product> fruits = productDao.getAllFruits();
        List<Product> vegetables = productDao.getAllVegetables();

        model.addAttribute("fruits", fruits);
        model.addAttribute("vegetables", vegetables);

        model.addAttribute(new Product());


        return "increasequantity";

    }


    @RequestMapping(value = "/increasequantity", method = RequestMethod.POST)
    public String updateGivenProduct(Product product) {
        System.out.println(product);
        if (product.getAmountKilo() != 0) {
            productDao.increaseProductByKilo(product.getAmountKilo(), product.getId());
        } else if (product.getAmountPiece() != 0) {
            productDao.increaseProductByPiece(product.getAmountPiece(), product.getId());
        }


        return "/";

    }


}
