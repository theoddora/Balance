package com.balance.exceptions;

/**
 * Created by ttosheva on 29/11/2016.
 */
public class PasswordsDontMatchException extends RuntimeException {

    public PasswordsDontMatchException(String message) {
        super(message);
    }
}
