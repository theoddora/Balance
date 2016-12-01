package com.balance.exceptions;

/**
 * Created by ttosheva on 30/11/2016.
 */
public class UsernameAlreadyExistsException extends RuntimeException {

    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
