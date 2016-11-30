package com.balance.controller;

import com.balance.exceptions.NoSuchUserException;
import com.balance.exceptions.PasswordsDontMatchException;
import com.balance.exceptions.UserAlreadyExistsException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by ttosheva on 30/11/2016.
 */
@ControllerAdvice
public class AppWideExceptionHandler {

    private static final String ERROR_MESSAGE = "errorMessage";

    @ExceptionHandler(PasswordsDontMatchException.class)
    public String logInWrongPassword(PasswordsDontMatchException e, Model model) {
        model.addAttribute(ERROR_MESSAGE, e.getMessage());
        return "log_in";
    }

    @ExceptionHandler(NoSuchUserException.class)
    public String logInWrongUsername(NoSuchUserException e, Model model) {
        model.addAttribute(ERROR_MESSAGE, e.getMessage());
        return "log_in";
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public String registerUser(UserAlreadyExistsException e, Model model) {
        model.addAttribute(ERROR_MESSAGE, e.getMessage());
        return "register";
    }
}
