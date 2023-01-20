package com.example.demo1.helper;

import com.example.demo1.controller.StartClientController;
import com.example.demo1.model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public abstract class NewScene {
    public static void newScene(URL fxmlLocation, double x, double y, String title, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();
        StartClientController startClientController = loader.getController();
        startClientController.loginUser(user);
        Scene scene = new Scene(root, 976, 552);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setX(x);
        stage.setY(y);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


}
