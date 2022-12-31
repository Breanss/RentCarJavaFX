package com.example.demo1.validation;

public class RentValidationException extends RuntimeException{
    public RentValidationException(String message){
        super(message);
    }
}
