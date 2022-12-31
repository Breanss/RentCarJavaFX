package com.example.demo1.validation;

public class CarValidationException extends RuntimeException{
    public CarValidationException(String message){
        super(message);
    }
}
