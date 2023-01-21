package com.example.demo1;

import com.example.demo1.model.User;
import com.example.demo1.service.UserService;
import com.example.demo1.service.UserServiceImpl;
import com.example.demo1.validation.RegisterValidate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterUserValidationTest {
    User user;
    RegisterValidate registerValidate = new RegisterValidate();

    @Test
    void correct_password() {
        user = new User();
        user.setPassword("qwerty123");

        String message = "";

        try {
            registerValidate.password(user.getPassword());
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("", message);
    }

    @Test
    void the_password_should_contain_least_6_characters(){
        user = new User();
        user.setPassword("qwery");

        String message = "";

        try {
            registerValidate.password(user.getPassword());
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Hasło powinno zawierać minimum 6 znaków!", message);
    }

    @Test
    void the_password_cannot_contain_only_letters(){
        user = new User();
        user.setPassword("qwerty");

        String message = "";

        try {
            registerValidate.password(user.getPassword());
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Hasło nie może zawierać tylko liter!", message);
    }

    @Test
    void correct_user_name() {
        user = new User();
        user.setUserName("test");

        String message = "";

        try {
            registerValidate.username(user.getUserName());
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("", message);
    }

    @Test
    void the_user_name_should_contain_least_3_characters(){
        user = new User();
        user.setUserName("as");

        String message = "";

        try {
            registerValidate.username(user.getUserName());
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Nazwa użytkownika powinna zawierać minimum 3 znaki!", message);
    }

    @Test
    void username_already_exists_in_the_database(){
        UserService userService = new UserServiceImpl();
        User user = new User();
        user.setUserName("test");
        user.setAdmin(true);
        user.setPassword("qwerty123");
        user.setEmail("test@gmail.com");
        userService.insertUser(user);

        String message = "";

        try {
            registerValidate.username(user.getUserName());
        } catch (Exception e) {
            message = e.getMessage();
        }
        userService.deleteUserByEmail(user.getEmail());
        assertEquals("Taka nazwa użytkownika już istnieje w bazie!", message);
    }

    @Test
    void correct_email() {
        user = new User();
        user.setEmail("test@gmail.com");

        String message = "";

        try {
            registerValidate.email(user.getEmail());
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("", message);
    }

    @Test
    void email_already_exists_in_the_database(){
        UserService userService = new UserServiceImpl();
        User user = new User();
        user.setUserName("test");
        user.setAdmin(true);
        user.setPassword("qwerty123");
        user.setEmail("test@gmail.com");
        userService.insertUser(user);

        String message = "";

        try {
            registerValidate.email(user.getEmail());
        } catch (Exception e) {
            message = e.getMessage();
        }
        userService.deleteUserByEmail(user.getEmail());
        assertEquals("Taki email już istnieje w bazie!", message);
    }
}
