package com.example.demo1.validation;

public class RegisterValidate extends Validators {
    public void password(String text) {
        if (minimumLengthIsBad(text, 6)) {
            throw new UserValidationException("Hasło powinno zawierać minimum 6 znaków!");
        }

        if (canNotOnlyContainLetters(text)) {
            throw new UserValidationException("Hasło nie może zawierać tylko liter!");
        }

        if (maximumLengthIsBad(text, 19)) {
            throw new UserValidationException("Hasło powinno zawierać maximum 20 znaków!");
        }
    }

    public void username(String text) {
        if (minimumLengthIsBad(text, 3)) {
            throw new UserValidationException("Nazwa użytkownika powinna zawierać minimum 3 znaki!");
        }

        if (canNotTheSameUsername(text)) {
            throw new UserValidationException("Taka nazwa użytkownika już istnieje w bazie!");
        }

        if (maximumLengthIsBad(text, 19)) {
            throw new UserValidationException("Nazwa użytkownika powinna zawierać maximum 20 znaków!");
        }
    }

    public void email(String text) {
        if (minimumLengthIsBad(text, 3)) {
            throw new UserValidationException("Email powinien zawierać minimum 3 znaki!");
        }

        if (canNotTheSameEmail(text)) {
            throw new UserValidationException("Taki email już istnieje w bazie!");
        }

        if (incorrectEmail(text)) {
            throw new UserValidationException("Nieprawidłowy format meila!");
        }

        if (maximumLengthIsBad(text, 45)) {
            throw new UserValidationException("Email powinien zawierać maximum 45 znaków!");
        }
    }
}
