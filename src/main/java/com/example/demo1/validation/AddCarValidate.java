package com.example.demo1.validation;

public class AddCarValidate extends Validators {
    public void brand(String text) {
        if (minimumLengthIsBad(text, 2)) {
            throw new CarValidationException("Marka powinna zawierać minimum 2 znaki!");
        }
        if (canOnlyContainLetters(text)) {
            throw new CarValidationException("Marka może zawierać tylko litery!");
        }
        if (maximumLengthIsBad(text, 20)) {
            throw new CarValidationException("Marka może mieź maximum 20 znaków!");
        }
    }

    public void model(String text) {
        if (minimumLengthIsBad(text, 2)) {
            throw new CarValidationException("Model powinien zawierać minimum 2 znaki!");
        }
        if (canOnlyContainLetters(text)) {
            throw new CarValidationException("Model może zawierać tylko litery!");
        }
        if (maximumLengthIsBad(text, 20)) {
            throw new CarValidationException("Model może mieź maximum 20 znaków!");
        }
    }

    public void engine(String text) {
        if (minimumLengthIsBad(text, 2)) {
            throw new CarValidationException("Silnik powinien zawierać minimum 1 znak!");
        }
        if (maximumLengthIsBad(text, 20)) {
            throw new CarValidationException("Silnik może mieź maximum 20 znaków!");
        }
    }

    public void price(String number) {
        if (canOnlyContainNumber(number)) {
            throw new CarValidationException("Cena może zawierać tylko liczby!");
        }
        if (priceMustBeMore0(number)) {
            throw new CarValidationException("Cena musi być większa od 0!");
        }
    }

    public void image(String image) {
        if (mustBeOtherImage(image)) {
            throw new CarValidationException("Obraz musi zostać zaimportowany!");
        }
    }
}
