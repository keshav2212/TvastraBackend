package com.tvastra.exceptions;

public class UserNameAlreadyExistsException extends Exception {
    public UserNameAlreadyExistsException() {
        super("User name already exists");
    }
}
