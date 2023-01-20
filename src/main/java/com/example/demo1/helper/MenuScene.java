package com.example.demo1.helper;

import com.example.demo1.controller.CarController;
import com.example.demo1.controller.RentController;
import com.example.demo1.controller.StartClientController;
import com.example.demo1.controller.UsersController;
import com.example.demo1.model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public abstract class MenuScene {
    public static void rentScene(User user, AnchorPane anchorPane) throws IOException {
        URL fxmlLocation = MenuScene.class.getResource("/com/example/demo1/rent.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();
        RentController rentController = loader.getController();
        rentController.loginUser(user);
        AnchorPane pane = (AnchorPane) root;
        anchorPane.getChildren().setAll(pane);
    }

    public static void homeScene(User user, AnchorPane anchorPane) throws IOException {
        URL fxmlLocation = MenuScene.class.getResource("/com/example/demo1/startclient.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();
        StartClientController startClientController = loader.getController();
        startClientController.loginUser(user);
        AnchorPane pane = (AnchorPane) root;
        anchorPane.getChildren().setAll(pane);
    }

    public static void carsScene(User user, AnchorPane anchorPane) throws IOException {
        URL fxmlLocation = MenuScene.class.getResource("/com/example/demo1/cars.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();
        CarController carController = loader.getController();
        carController.loginUser(user);
        AnchorPane pane = (AnchorPane) root;
        anchorPane.getChildren().setAll(pane);
    }

    public static void usersScene(User user, AnchorPane anchorPane) throws IOException {
        URL fxmlLocation = MenuScene.class.getResource("/com/example/demo1/users.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();
        UsersController usersController = loader.getController();
        usersController.loginUser(user);
        AnchorPane pane = (AnchorPane) root;
        anchorPane.getChildren().setAll(pane);
    }



    public static void logoutScene(AnchorPane anchorPane) throws IOException {
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(MenuScene.class.getResource("/com/example/demo1/login.fxml")));
        anchorPane.getChildren().setAll(pane);
    }
}
