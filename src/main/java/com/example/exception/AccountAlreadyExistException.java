package com.example.exception;

public class AccountAlreadyExistException extends RuntimeException {
    public AccountAlreadyExistException() {
        super();
    }

    public AccountAlreadyExistException(String message) {
        super(message);
    }
    
}
