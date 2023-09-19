package com.example.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class InvalidInputException extends RuntimeException {
    private String message;
    
}
