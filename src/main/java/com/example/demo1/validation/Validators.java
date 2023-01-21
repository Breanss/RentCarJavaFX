package com.example.demo1.validation;

import com.example.demo1.model.User;
import com.example.demo1.service.UserService;
import com.example.demo1.service.UserServiceImpl;

import java.util.regex.Pattern;

public abstract class Validators {
    public boolean minimumLengthIsBad(String text, int minLength) {
       if(text==null)
            return true;
        return text.length() < minLength;
    }

    public boolean maximumLengthIsBad(String text, int maxLength) {
        return text.length() > maxLength;
    }

    public boolean canNotOnlyContainLetters(String text) {
        Pattern pattern = Pattern.compile("[A-Za-zĄąĘęÓóŚśŁłŹźŻżĆćŃń]+");
        return pattern.matcher(text).matches();
    }

    public boolean canOnlyContainLetters(String text) {
        Pattern pattern = Pattern.compile("[A-Za-zĄąĘęÓóŚśŁłŹźŻżĆćŃń]+");
        return !pattern.matcher(text).matches();
    }

    public boolean canOnlyContainNumber(String text) {
        Pattern pattern = Pattern.compile("[0-9.]+");
        return !pattern.matcher(text).matches();
    }

    public boolean canNotTheSameUsername(String text) {
        UserService userService = new UserServiceImpl();
        User user = userService.findUserByUserName(text);
        return user.getUserName() != null;
    }

    public boolean canNotTheSameEmail(String text) {
        UserService userService = new UserServiceImpl();
        User user = userService.findUserByEmail(text);
        return user.getEmail() != null;
    }

    public boolean incorrectEmail(String text) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        return !pattern.matcher(text).matches();
    }

    public boolean mustBeOtherImage(String text) {
        String [] tab;
        try {
            tab = text.split("/");
        }catch (ExceptionInInitializerError e){
            return true;
        }
        return !tab[tab.length - 2].equals("img") && tab[tab.length-8].equals("src");

    }

    public boolean priceMustBeMore0(String text) {
        return (Float.parseFloat(text) <= 0);
    }
}
