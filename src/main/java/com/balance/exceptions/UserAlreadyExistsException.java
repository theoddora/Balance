package com.balance.exceptions;

/**
 * Created by ttosheva on 30/11/2016.
 */
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
