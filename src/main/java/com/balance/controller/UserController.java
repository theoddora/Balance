package com.balance.controller;

import com.balance.dao.UserDAO;
import com.balance.model.Product;
import com.balance.model.User;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by ttosheva on 23/11/2016.
 */


@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;

    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    //register
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(@Valid User user, Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "register";
        }
        userDAO.createUser(user);

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
                            HttpServletRequest request, Model model) {

        User user = userDAO.getUser(username, password);
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(60 * 60);
//        session.setAttribute("user", user);
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
}
