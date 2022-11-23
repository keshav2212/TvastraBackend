package com.shopex.exceptions;

public class UserNameAlreadyExistsException extends Exception {
    public UserNameAlreadyExistsException() {
        super("User name already exists");
    }
}
