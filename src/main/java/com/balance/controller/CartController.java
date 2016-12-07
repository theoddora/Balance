package com.balance.controller;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.balance.model.Product;

/**
 * Created by ttosheva on 07/12/2016.
 */
@RestController
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @RequestMapping(value = " /removeFromCart", method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity removeFromCart(@RequestParam Integer id, Principal principal, HttpSession session, HttpServletRequest request) {

        Map<Product, Double> currentCart = (Map<Product, Double>) session.getAttribute("cart");
        Product productToDelete = null;
        for (Product product : currentCart.keySet()) {
            if (id.equals(product.getId())) {
                productToDelete = product;
                logger.info("Product with id " + product.getId() + " was found in cart.");
                break;
            }
        }

        double priceToShow = (double) session.getAttribute("priceToShow");

        if (productToDelete != null) {
            logger.info("The price in the cart before removing the product is " + priceToShow);

            double price = (productToDelete.getPrice()
                - (productToDelete.getPrice() * productToDelete.getDiscount())) * currentCart.get(productToDelete);
            priceToShow -= price;
            session.setAttribute("priceToShow", priceToShow);
            currentCart.remove(productToDelete);

            logger.info("Product with id " + productToDelete.getId() + " was removed from cart.");
            logger.info("The price in the cart is now " + priceToShow);
        }

        return ResponseEntity.ok(priceToShow);
    }
}
