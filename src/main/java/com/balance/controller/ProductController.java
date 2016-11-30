package com.balance.controller;

import com.balance.dao.ProductDao;
import com.balance.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    //cart

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String addToCart(@RequestParam("productId") int id, @RequestParam("amount") double amount, Model model) {
        Map<Product, Double> cart = new HashMap<>();

        Product product = productDao.findProductById(id);

        boolean isForKilo = product.getIsForKilo();
        String message = null;

        if (productDao.hasEnoughAmount(amount, id, isForKilo)) {
            synchronized (productDao) {
                if (isForKilo) {
                    productDao.decreaseProductByKilo(amount, id);
                } else {
                    productDao.decreaseProductByPiece((int) amount, id);
                }
            }
            cart.put(product, amount);
            message = "The selected amount has been added to the cart";

        } else {
            cart.put(product, 0.0);
            double currentAmount = productDao.getCurrentAmount(id, isForKilo);
            message = "There is not enough amount from " + product.getName() + ". The current amount is " + currentAmount + ".";
        }
        model.addAttribute("message", message);
        model.addAttribute("cart", cart);
        return "cart";

    }

    //returning view
    @RequestMapping(value = "/increasequantity", method = RequestMethod.GET)
    public String increaseQuantityJSP(Model model) {
        

        List<Product> fruits = productDao.getAllFruits();
        List<Product> vegetables = productDao.getAllVegetables();

        model.addAttribute("fruits", fruits);
        model.addAttribute("vegetables", vegetables);

        model.addAttribute(new Product());


        return "increasequantity";

    }

    //updating products
    @RequestMapping(value = "/increasequantity", method = RequestMethod.POST)
    public String increaseProductsQuantity(Product product) {
        System.out.println(product);
        if(product.getAmountKilo() != 0) {
            productDao.increaseProductByKilo(product.getAmountKilo(), product.getId());
        }else if(product.getAmountPiece() != 0){
            productDao.increaseProductByPiece(product.getAmountPiece(), product.getId());
        }


        return "/";

    }


    //returning view
    @RequestMapping(value = "/addproducts", method = RequestMethod.GET)
    public String addProductsJSP(Model model) {

        List<Product> fruits = productDao.getAllFruits();
        List<Product> vegetables = productDao.getAllVegetables();

        model.addAttribute("fruits", fruits);
        model.addAttribute("vegetables", vegetables);


        model.addAttribute(new Product());


        return "addproducts";

    }

    //inserting product
    @RequestMapping(value = "/addproducts", method = RequestMethod.POST)
    public String addProducts(Product product) {

        productDao.insertProduct(product);

        return "addproducts";

    }





}
