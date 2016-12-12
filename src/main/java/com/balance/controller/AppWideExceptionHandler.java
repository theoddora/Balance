package com.balance.controller;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.balance.exceptions.EmailAlreadyExistsException;
import com.balance.exceptions.UsernameAlreadyExistsException;
import com.balance.model.User;

@ControllerAdvice
public class AppWideExceptionHandler {

    private static final String ERROR_MESSAGE = "errorMessage";

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public String registerUser(UsernameAlreadyExistsException e, Model model) {
        model.addAttribute(ERROR_MESSAGE, e.getMessage());
        model.addAttribute("user", new User());
        return "register";
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public String registerUser(EmailAlreadyExistsException e, Model model) {
        model.addAttribute(ERROR_MESSAGE, e.getMessage());
        model.addAttribute("user", new User());
        return "register";
    }

}
