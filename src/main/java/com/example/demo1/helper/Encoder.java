package com.example.demo1.helper;

import java.util.Base64;

public abstract class Encoder {
    public static String encodePassword(String password){
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(password.getBytes());
    }

}
