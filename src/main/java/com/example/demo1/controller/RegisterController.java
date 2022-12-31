package com.example.demo1.controller;

import com.example.demo1.model.User;
import com.example.demo1.service.UserService;
import com.example.demo1.service.UserServiceImpl;
import com.example.demo1.validation.RegisterValidate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.lang.String;
import java.net.URL;
import java.util.Base64;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    AnchorPane anchorPane;

    @FXML
    Text errorPasswordText;
    @FXML
    Text errorEmailText;

    @FXML
    Text errorUsernameText;

    @FXML
    Text registerSucces;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    private TextField email;

    private final UserService userService;
    private final RegisterValidate registerValidate;

    public RegisterController() {
        userService = new UserServiceImpl();
        registerValidate = new RegisterValidate();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        changeTextListener(password, errorPasswordText);
        changeTextListener(username, errorUsernameText);
        changeTextListener(email, errorEmailText);
    }

    @FXML
    public void handleButtonActionRegisterUser(ActionEvent event) {
        User user = new User();
        boolean error = false;
        errorClear();
        user.setEmail(email.getText());
        user.setUserName(username.getText());

        try {
            registerValidate.password(password.getText());
        } catch (Exception e) {
            errorPasswordText.setText(e.getMessage());
            error = true;
            borderError(password);
        }

        try {
            registerValidate.username(username.getText());
        } catch (Exception e) {
            errorUsernameText.setText(e.getMessage());
            error = true;
            borderError(username);
        }

        try {
            registerValidate.email(email.getText());
        } catch (Exception e) {
            errorEmailText.setText(e.getMessage());
            error = true;
            borderError(email);
        }

        Base64.Encoder encoder = Base64.getEncoder();
        String encoded = encoder.encodeToString(password.getText().getBytes());
        user.setPassword(encoded);
        if (!error) {
            userService.insertUser(user);
            email.setText("");
            password.setText("");
            username.setText("");
            registerSucces.setText("Użytkownik został dodany!");
        }
    }

    @FXML
    public void handleButtonActionLogin(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/demo1/login.fxml")));
        anchorPane.getChildren().setAll(pane);
    }

    public void errorClear() {
        errorPasswordText.setText("");
        errorUsernameText.setText("");
    }

    private void changeTextListener(TextField text, Text error) {
        text.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                if (!text.getText().equals("")) {
                    text.setStyle("-fx-border-width: 0px;");
                    error.setText("");
                    registerSucces.setText("");
                }
            }
        });
    }

    private void borderError(TextField f) {
        f.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
    }
}
