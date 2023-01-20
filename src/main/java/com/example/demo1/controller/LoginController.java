package com.example.demo1.controller;


import com.example.demo1.helper.NewScene;
import com.example.demo1.model.User;
import com.example.demo1.service.UserService;
import com.example.demo1.service.UserServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    @FXML
    AnchorPane anchorPane;
    @FXML
    private Button login_button;
    @FXML
    private TextField password;
    @FXML
    private TextField username;
    @FXML
    private Text errorLoginPassword;
    private final UserService userService;


    public LoginController() {
        userService = new UserServiceImpl();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        changeTextListener(password, errorLoginPassword);
        changeTextListener(username, errorLoginPassword);
    }

    @FXML
    public void handleButtonActionlogin(ActionEvent event) {
        User user = userService.findUserByUserNameAndPassword(username.getText(), password.getText());

        try {
            username.setText("");
            password.setText("");
            if (!(user.getUserName() == null && user.getPassword() == null)) {
                double x = login_button.getScene().getWindow().getX();
                double y = login_button.getScene().getWindow().getY();
                login_button.getScene().getWindow().hide();
                URL fxmlLocation = getClass().getResource("/com/example/demo1/startclient.fxml");
                NewScene.newScene(fxmlLocation, x, y, "Rental cars", user);
            } else {
                errorLoginPassword.setText("Nieprawidłowy login lub hasło!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleButtonActionRegister(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/demo1/register.fxml")));
        anchorPane.getChildren().setAll(pane);
    }

    private void changeTextListener(TextField text, Text error) {
        text.textProperty().addListener((observableValue, s, s2) -> {
            if (!text.getText().equals("")) {
                error.setText("");
            }
        });
    }


}
