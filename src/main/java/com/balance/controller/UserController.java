package com.balance.controller;

import com.balance.dao.UserDAO;
import com.balance.exceptions.PasswordsDontMatchException;
import com.balance.model.Product;
import com.balance.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
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

/**
 * Created by ttosheva on 23/11/2016.
 */


@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;

    private static final String DUPLICATE_USERNAME = "User with that username/email already exists.";
    private static final String WRONG_USERNAME = "You have entered a wrong username";
    private static final String WRONG_PASSWORD = "You have entered a wrong password";

    //register
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(@Valid User user, Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "register";
        }
        try {
            userDAO.createUser(user);
        }catch (DuplicateKeyException e) {
            model.addAttribute("errorMessage", DUPLICATE_USERNAME);
            return "register";
        }

        System.out.println("username: " + user.getUsername());
        System.out.println("password: " + user.getPassword());
        System.out.println("email: " + user.getEmail());
        System.out.println("name: " + user.getName());
        return "forward:/" + user.getUsername();

    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegisterUserPage(Model model) {
        model.addAttribute(new User());
        return "register";
    }

    //log in
    @RequestMapping(value = "/log_in", method = RequestMethod.POST)
    public String logInUser(@RequestParam(value = "username") String username,
                            @RequestParam(value = "password") String password,
                            HttpServletRequest request, Model model) {
        User user = null;
        try {
            user = userDAO.getUser(username, password);
        } catch (IncorrectResultSizeDataAccessException e) {
            model.addAttribute("errorMessage", WRONG_USERNAME);
            return "log_in";
        } catch (PasswordsDontMatchException e) {
            model.addAttribute("errorMessage", WRONG_PASSWORD);
            return "log_in";
        }
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(60 * 60);
        session.setAttribute("user", user);
        session.setAttribute("cart", new HashMap<Product, Double>());
        return "redirect:/index";
    }

    @RequestMapping(value = "/log_in", method = RequestMethod.GET)
    public String getLogInPage() {
        return "log_in";
    }

    //profile
    @RequestMapping(value="/{username}", method = RequestMethod.POST)
    public String viewProfilePage(@PathVariable String username, Model model) {
        User user = userDAO.findByUsername(username);
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "profile_page";
    }
}
