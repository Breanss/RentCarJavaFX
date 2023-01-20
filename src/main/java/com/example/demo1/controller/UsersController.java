package com.example.demo1.controller;

import com.example.demo1.helper.MenuScene;
import com.example.demo1.model.User;
import com.example.demo1.service.UserService;
import com.example.demo1.service.UserServiceImpl;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UsersController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, Integer> lp;
    @FXML
    private TableColumn<User, String> username;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TextField usernameField;
    @FXML
    private Text message;
    @FXML
    private TextField emailField;
    private User user;
    private final UserService userService;

    public void loginUser(User user) {
        this.user = user;
    }

    public UsersController() {
        userService = new UserServiceImpl();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<User> list = userService.observeFindAllUserNoAdmin();
        lp.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        username.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        table.setItems(list);
        changeTextListener(emailField);
    }

    @FXML
    private void handleClickActionRecord(Event mouseEvent) {
        User user = table.getSelectionModel().getSelectedItem();
        usernameField.setText(user.getUserName());
        emailField.setText(user.getEmail());
    }

    @FXML
    private void changeToAdmin(Event mouseEvent) throws IOException {
        String email = emailField.getText();
        if (!email.isEmpty()) {
            message.setFill(Color.GREEN);
            message.setText("Pomyślnie zmieniono uprawnienia!");
            userService.updateUserToAdmin(email);
            MenuScene.usersScene(user, anchorPane);
        } else {
            message.setFill(Color.RED);
            message.setText("Zaznacz użytkownika któremu zmienić uprawnienia!");
        }
    }

    @FXML
    public void handleButtonActionHome(ActionEvent event) throws IOException {
        MenuScene.homeScene(user, anchorPane);
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

    private void changeTextListener(TextField text) {
        text.textProperty().addListener((observableValue, s, s2) -> message.setText(""));
    }
}
