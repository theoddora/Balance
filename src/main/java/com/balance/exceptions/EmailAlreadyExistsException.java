package com.balance.exceptions;

/**
 * Created by ttosheva on 01/12/2016.
 */
public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}