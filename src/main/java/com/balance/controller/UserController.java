package com.balance.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.balance.security.SecurityUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.balance.dao.ProductDao;
import com.balance.dao.UserDAO;
import com.balance.model.Order;
import com.balance.model.Product;
import com.balance.model.User;
import com.balance.service.OrderManager;

/**
 * Created by ttosheva on 23/11/2016.
 */
@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    ProductDao productDao;

    @Autowired
    OrderManager orderManager;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    //register
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(@Valid User user, Errors errors) {

        logger.info("A user wants to register.");
        if (errors.hasErrors()) {
            return "register";
        }
        userDAO.createUser(user);

        logger.info("A user with username " + user.getUsername() + " has registered.");
        return "redirect:/emailconfirm";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegisterUserPage(Model model) {
        model.addAttribute(new User());
        return "register";
    }

    @RequestMapping(value = "/emailconfirm", method = RequestMethod.GET)
    public String getConfirmEmailPage() {
        return "emailconfirm";
    }

    //log in
    @RequestMapping(value = "/log_in", method = RequestMethod.POST)
    public String logInUser(@RequestParam(value = "username") String username,
                            @RequestParam(value = "password") String password,
                            HttpServletRequest request) {

        userDAO.getUser(username, password);
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(60 * 60);
        //session.setAttribute("user", user);
        session.setAttribute("cart", new HashMap<Product, Double>());
        return "redirect:/index";
    }

    @RequestMapping(value = "/log_in", method = RequestMethod.GET)
    public String getLogInPage() {
        return "log_in";
    }

    //profile
    @RequestMapping(value = "/{username}", method = RequestMethod.POST)
    public String viewProfilePage(@PathVariable String username, Model model) {

        User user = userDAO.findByUsername(username);
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "profile_page";

    }

    @RequestMapping(value = "/addToBuy", method = RequestMethod.GET)
    public String addProductsToBuy(Model model, HttpSession session, Principal principal) {
        if (principal == null) {
            return "log_in";
        }

        String username = principal.getName();

        User user = userDAO.findByUsername(username);
        long userId = user.getId();
        Map<Product, Double> cart = (Map<Product, Double>) session.getAttribute("cart");

        if (cart == null) {
            return "/";
        }


        for (Product product : cart.keySet()) {
            boolean isForKilo = product.getIsForKilo();
            int id = product.getId();
            synchronized (productDao) {
                if (isForKilo) {
                    double amount = product.getAmountKilo();
                    productDao.decreaseProductByKilo(amount, id);
                } else {
                    double amount = product.getAmountPiece();
                    productDao.decreaseProductByPiece( (int)amount, id);
                }
            }
            int productId = product.getId();
            double amount = cart.get(product);


            orderManager.placeOrder(productId, userId, amount);

        }

        Set<Order> orders = orderManager.getOrders(userId);

        model.addAttribute("orders", orders);


        return "profile_page";
    }

}
