package com.example.demo1.service;

import com.example.demo1.model.User;
import javafx.collections.ObservableList;

import java.util.List;

public interface UserService {
    User findUserByUserNameAndPassword(String username, String password);

    void insertUser(User user);

    User findUserByUserName(String username);

    void deleteUserByEmail(String email);

    User findUserByEmail(String email);

    List<User> findAll();

    ObservableList<User> observeFindAllUserNoAdmin();

    void updateUserToAdmin(String email);
}
