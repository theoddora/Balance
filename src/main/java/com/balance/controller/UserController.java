package com.balance.controller;

import com.balance.dao.UserDAO;
import com.balance.model.User;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by ttosheva on 23/11/2016.
 */


@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;

    private static final String DUPLICATE_USERNAME = "User with that username/email already exists.";

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
        return "redirect:/" + user.getUsername();

    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegisterUserPage(Model model) {
        model.addAttribute(new User());
        return "register";
    }

    //profile
    @RequestMapping(value="/{username}", method = RequestMethod.POST)
    public String viewProfilePage(@PathVariable String username, Model model) {
        User user = userDAO.getUser(username);
        model.addAttribute("user", user);
        return "profile_page";
    }

}
