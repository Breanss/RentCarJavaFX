package com.example.demo1.controller;

import com.example.demo1.helper.MenuScene;
import com.example.demo1.model.Car;
import com.example.demo1.model.User;
import com.example.demo1.service.CarService;
import com.example.demo1.service.CarServiceImpl;
import com.example.demo1.validation.RentValidate;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RentController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button usersButton;
    @FXML
    private TableView<Car> table;
    @FXML
    private TableColumn<Car, Integer> lpTableColumn;
    @FXML
    private TableColumn<Car, String> brandTableColumn;
    @FXML
    private TableColumn<Car, String> engineTableColumn;
    @FXML
    private TableColumn<Car, String> modelTableColumn;
    @FXML
    private TableColumn<Car, Float> priceTableColumn;
    @FXML
    private TextField dayField;
    @FXML
    private TextField priceField;
    @FXML
    private Text confirmantionPayment;
    private User user;
    private final CarService carService;
    private final RentValidate rentValidate;
    private Float price;

    public RentController() {
        rentValidate = new RentValidate();
        carService = new CarServiceImpl();
    }

    public void loginUser(User user) {
        this.user = user;
        if (!user.getAdmin()) offForClient();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Car> list = carService.observeFindlCarWhereAvalibleTrue();
        lpTableColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("id"));
        brandTableColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("brand"));
        modelTableColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
        engineTableColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("engine"));
        priceTableColumn.setCellValueFactory(new PropertyValueFactory<Car, Float>("price"));
        table.setItems(list);
        changeTextListener(dayField);
    }

    @FXML
    public void handleButtonActionUsers(ActionEvent event) throws IOException {
        MenuScene.usersScene(user, anchorPane);
    }

    @FXML
    public void handleButtonActionHome(ActionEvent event) throws IOException {
        MenuScene.homeScene(user, anchorPane);
    }

    @FXML
    public void handleButtonActionCar(ActionEvent event) throws IOException {
        MenuScene.carsScene(user, anchorPane);
    }

    @FXML
    public void handleButtonActionLogout(ActionEvent event) throws IOException {
        MenuScene.logoutScene(anchorPane);
    }

    @FXML
    public void handleButtonActionPay(ActionEvent event) {
        boolean error = false;
        try {
            rentValidate.dayNumber(dayField.getText());
        } catch (Exception e) {
            error = true;
        }
        if (!error) {
            confirmantionPayment.setText("Zapłacono za wypożyczenie " + priceField.getText() + " zł na " + dayField.getText() + " dni");
        }
    }

    @FXML
    private void handleClickActionRecord(Event mouseEvent) {
        Car car;
        boolean error = false;
        if ((car = table.getSelectionModel().getSelectedItem()) != null) {
            price = car.getPrice();
            if (!dayField.getText().isEmpty()) {
                try {
                    rentValidate.dayNumber(dayField.getText());
                } catch (Exception e) {
                    error = true;
                }
                if (!error) {
                    float tmp = Float.parseFloat(dayField.getText()) * price;
                    priceField.setText(Float.toString(tmp));
                }
            }
            dayField.setDisable(false);
        }
    }

    private void changeTextListener(TextField text) {
        text.textProperty().addListener((observableValue, s, s2) -> {
            boolean error = false;
            try {
                rentValidate.dayNumber(dayField.getText());
            } catch (Exception e) {
                dayField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                error = true;
            }
            if (!error) {
                dayField.setStyle("-fx-border-width: 0px;");
                float tmp = Float.parseFloat(dayField.getText()) * price;
                priceField.setText(Float.toString(tmp));
            }
        });
    }

    public void offForClient() {
        usersButton.setVisible(false);
    }


}
