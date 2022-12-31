package com.example.demo1.validation;

public class RentValidate extends Validators {
    public void dayNumber(String text) {
        if (canOnlyContainNumber(text)) {
            throw new RentValidationException("Cena może zawierać tylko liczby!");
        }
    }
}
