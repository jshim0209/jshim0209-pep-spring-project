package com.example.exception;

public class AccountAlreadyExistException extends Exception {
    public AccountAlreadyExistException() {
        super();
    }

    public AccountAlreadyExistException(String message) {
        super(message);
    }
    
}
