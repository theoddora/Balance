package com.balance.controller;

import com.balance.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by ttosheva on 23/11/2016.
 */
@Controller
public class UserController {


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(User user) {

        System.out.println("username: " + user.getUserName());
        System.out.println("password: " + user.getPassword());
        System.out.println("email: " + user.getEmail());
        System.out.println("name: " + user.getName());
        return "register";
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterUserPage(Model model) {
        model.addAttribute(new User());
        return "register";
    }
}
