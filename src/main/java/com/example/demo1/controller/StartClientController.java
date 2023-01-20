package com.example.demo1.controller;

import com.example.demo1.helper.MenuScene;
import com.example.demo1.model.User;
import com.example.demo1.service.CarService;
import com.example.demo1.service.CarServiceImpl;
import com.example.demo1.service.UserService;
import com.example.demo1.service.UserServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StartClientController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Text countUsers;
    @FXML
    private Text countCars;
    @FXML
    private Button usersButton;
    @FXML
    private Text userLoginText;
    private final UserService userService;
    private final CarService carService;
    private User user;

    public StartClientController() {
        carService = new CarServiceImpl();
        userService = new UserServiceImpl();
    }

    public void loginUser(User user) {
        this.user = user;
        userLoginText.setText("Witaj " + user.getUserName() + "!");
        if (!user.getAdmin()) offForClient();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<User> users = userService.findAll();
        countUsers.setText(Integer.toString(users.size()));
        countCars.setText(Integer.toString(carService.countCars()));
    }

    @FXML
    public void handleButtonActionUsers(ActionEvent event) throws IOException {
        MenuScene.usersScene(user, anchorPane);
    }

    @FXML
    public void handleButtonActionRent(ActionEvent event) throws IOException {
        MenuScene.rentScene(user, anchorPane);
    }

    @FXML
    public void handleButtonActionCar(ActionEvent event) throws IOException {
        MenuScene.carsScene(user, anchorPane);
    }

    @FXML
    public void handleButtonActionLogout(ActionEvent event) throws IOException {
        MenuScene.logoutScene(anchorPane);
    }


    public void offForClient() {
        usersButton.setVisible(false);
    }


}
