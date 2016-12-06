package com.balance.controller;

import java.io.File;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.balance.dao.ProductDao;
import com.balance.model.Product;

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

        double totalPrice = 0.0;
        Double priceToShow = 0.0;


        boolean isForKilo = product.getIsForKilo();
        String message = null;

        if (productDao.hasEnoughAmount(amount, id, isForKilo)) {

            cart.put(product, amount);
            message = "The selected amount has been added to the cart";

        } else {
            if (cart.containsKey(product)) {
                cart.remove(product);
            }
            double currentAmount = productDao.getCurrentAmount(id, isForKilo);
            message = "There is not enough amount from " + product.getName() + ". The current amount is " + currentAmount + ".";
        }
        for (Product productInCart : cart.keySet()) {

            double price = productInCart.getPrice() - (productInCart.getPrice() * productInCart.getDiscount());
            totalPrice += cart.get(productInCart) * price;

        }
        priceToShow = totalPrice;
        priceToShow = Math.floor(priceToShow * 100) / 100;


        model.addAttribute("message", message);
        model.addAttribute("cart", cart);
        session.setAttribute("priceToShow", priceToShow);
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
        if (product.getAmountKilo() != 0) {
            productDao.increaseProductByKilo(product.getAmountKilo(), product.getId());
        } else if (product.getAmountPiece() != 0) {
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
    public String addProducts(Product product, @RequestParam("file") MultipartFile
        file) {

        productDao.insertProduct(product);
        String productName = product.getName();

        try {
            file.transferTo(new File(productName + ".jpg"));

        } catch (Exception e) {
            System.out.println(e);
        }

        return "addproducts";

    }

    //returning view
    @RequestMapping(value = "/manageproducts", method = RequestMethod.GET)
    public String manageProductsJSP(Model model) {

        List<Product> products = productDao.getAllProducts();
        for (Product product : products) {
            System.out.println(product);
        }
        model.addAttribute("products", products);

        return "manageproducts";
    }

    //managing products
    @RequestMapping(value = "/manageproducts", method = RequestMethod.POST)
    public String manageProducts(Model model) {

        return "manageproducts";

    }

}
