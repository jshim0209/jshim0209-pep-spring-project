package com.example.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.dto.ErrorDto;
import com.example.exception.AccountAlreadyExistException;
import com.example.exception.InvalidCredentialsException;
import com.example.exception.InvalidInputException;
import com.example.exception.NotFoundException;

@ControllerAdvice(basePackages = { "com.example.controller" })
@ResponseBody
public class SocialMediaControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidInputException.class)
    public ErrorDto handleInvalidInputException(InvalidInputException invalidInputException) {
        return new ErrorDto(invalidInputException.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AccountAlreadyExistException.class)
    public ErrorDto handleAccountAlreadyExistException(AccountAlreadyExistException accountAlreadyExistException) {
        return new ErrorDto(accountAlreadyExistException.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidCredentialsException.class)
    public ErrorDto handleInvalidCredentialsException(InvalidCredentialsException invalidCredentialsException) {
        return new ErrorDto(invalidCredentialsException.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NotFoundException.class)
    public ErrorDto handleNotFoundException(NotFoundException notFoundException) {
        return new ErrorDto("");
    }

}
