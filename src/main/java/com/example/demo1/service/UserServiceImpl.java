package com.example.demo1.service;

import com.example.demo1.model.User;
import com.example.demo1.repository.UserRepository;
import javafx.collections.ObservableList;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository = new UserRepository();

    @Override
    public User findUserByUserNameAndPassword(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username, password);
    }

    @Override
    public void insertUser(User user) {
        userRepository.insertUser(user);
    }

    @Override
    public User findUserByUserName(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void deleteUserByEmail(String email) {
        userRepository.deleteUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAllUser();
    }

    @Override
    public ObservableList<User> observeFindAllUserNoAdmin() {
        return userRepository.observeFindAllUserNoAdmin();
    }

    @Override
    public void updateUserToAdmin(String email) {
        userRepository.updateUserToAdmin(email);
    }
}
