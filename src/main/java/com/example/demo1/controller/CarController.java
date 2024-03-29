package com.example.demo1.controller;

import com.example.demo1.helper.MenuScene;
import com.example.demo1.model.Car;
import com.example.demo1.model.User;
import com.example.demo1.service.CarService;
import com.example.demo1.service.CarServiceImpl;
import com.example.demo1.validation.AddCarValidate;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class CarController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button usersButton;
    @FXML
    private TextField brandField;
    @FXML
    private TextField modelField;
    @FXML
    private TextField engineField;
    @FXML
    private TextField priceField;
    @FXML
    private MenuButton avalibleField;
    @FXML
    private Text errorMessageBrand;
    @FXML
    private Text errorMessageModel;
    @FXML
    private Text errorMessageEngine;
    @FXML
    private Text brandText;
    @FXML
    private Text modelText;
    @FXML
    private Text priceText;
    @FXML
    private Text avalibleText;
    @FXML
    private Text engineText;
    @FXML
    private Text errorMessagePrice;
    @FXML
    private Text errorMessageImage;
    @FXML
    private ImageView imageCar;
    @FXML
    private TableView<Car> table;
    @FXML
    private TableColumn<Car, Integer> lpTableColumn;
    @FXML
    private TableColumn<Car, String> brandTableColumn;
    @FXML
    private TableColumn<Car, String> modelTableColumn;
    @FXML
    private TableColumn<Car, String> engineTableColumn;
    @FXML
    private TableColumn<Car, Float> priceTableColumn;
    @FXML
    private TableColumn<Car, Boolean> avalibleTableColumn;
    @FXML
    private Button insertImageButton;
    @FXML
    private Button addCarButton;

    @FXML
    private Button deleteCarButton;

    @FXML
    private Button clearButton;
    private Integer id;
    private final CarService carService;
    private final AddCarValidate addCarValidate;
    private User user;

    public CarController() {
        carService = new CarServiceImpl();
        addCarValidate = new AddCarValidate();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Car> list = carService.observeFindAllCar();
        lpTableColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("id"));
        brandTableColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("brand"));
        modelTableColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
        engineTableColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("engine"));
        priceTableColumn.setCellValueFactory(new PropertyValueFactory<Car, Float>("price"));
        avalibleTableColumn.setCellValueFactory(new PropertyValueFactory<Car, Boolean>("avalible"));
        table.setItems(list);
        changeTextListener(modelField, errorMessageModel);
        changeTextListener(brandField, errorMessageBrand);
        changeTextListener(priceField, errorMessagePrice);
        changeTextListener(engineField, errorMessageEngine);
    }

    public void loginUser(User user) {
        this.user = user;
        if (!user.getAdmin()) offForClient();
    }

    @FXML
    public void handleButtonActionAddCar(ActionEvent event) throws IOException {
        Car car = new Car();
        errorClear();
        boolean error = false;
        try {
            addCarValidate.brand(brandField.getText());
        } catch (Exception e) {
            errorMessageBrand.setText(e.getMessage());
            error = true;
            borderError(brandField);
        }

        try {
            addCarValidate.model(modelField.getText());
        } catch (Exception e) {
            errorMessageModel.setText(e.getMessage());
            error = true;
            borderError(modelField);
        }

        try {
            addCarValidate.engine(engineField.getText());
        } catch (Exception e) {
            errorMessageEngine.setText(e.getMessage());
            error = true;
            borderError(engineField);
        }

        try {
            addCarValidate.price(priceField.getText());
        } catch (Exception e) {
            errorMessagePrice.setText(e.getMessage());
            error = true;
            borderError(priceField);
        }

        try {
            addCarValidate.image(imageCar.getImage().getUrl());
        } catch (Exception e) {
            errorMessageImage.setText(e.getMessage());
            error = true;
        }
        car.setBrand(brandField.getText());
        car.setEngine(engineField.getText());
        car.setModel(modelField.getText());
        String[] url = imageCar.getImage().getUrl().split("/");
        car.setImage("src/main/resources/com/example/demo1/img/"+url[url.length-1]);

        int czy = carService.isThere(car.getBrand(), car.getModel(), car.getEngine());

        if (!error && czy == 0) {
            car.setPrice(Float.parseFloat(priceField.getText()));
            car.setAvalible(avalibleField.getText().equals("Dostępny"));
            brandField.setText("");
            engineField.setText("");
            modelField.setText("");
            priceField.setText("");
            carService.insertCar(car);
            MenuScene.carsScene(user, anchorPane);
        }
    }

    @FXML
    public void handleButtonActionDeleteCar(ActionEvent event) throws IOException {
        if (id != null && !brandField.getText().isEmpty()) {
            carService.deleteById(id);
            MenuScene.carsScene(user, anchorPane);
        }
    }

    @FXML
    public void handleButtonActionClear(ActionEvent event) throws IOException {
        brandField.setText("");
        modelField.setText("");
        engineField.setText("");
        priceField.setText("");
        File file = new File("src/main/resources/com/example/demo1/img/image.png");
        Image image = new Image(file.toURI().toString());
        imageCar.setImage(image);
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
    public void handleButtonActionHome(ActionEvent event) throws IOException {
        MenuScene.homeScene(user, anchorPane);
    }

    @FXML
    public void handleButtonActionLogout(ActionEvent event) throws IOException {
        MenuScene.logoutScene(anchorPane);
    }

    @FXML
    public void handleActionDostepny(ActionEvent event) {
        avalibleField.setText("Dostępny");
    }

    @FXML
    public void handleActionNieDostepny(ActionEvent event) {
        avalibleField.setText("Niedostępny");
    }

    @FXML
    public void handleButtonActionInsertImage(ActionEvent event) {
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*jpg", "*png"));
        File file = open.showOpenDialog(anchorPane.getScene().getWindow());
        if (!(file == null)) {
            Image image = new Image(file.toURI().toString(), 500, 300, false, true);
            imageCar.setImage(image);
        }
    }

    @FXML
    private void handleClickActionRecord(Event mouseEvent) {
        Car car = table.getSelectionModel().getSelectedItem();
        if (car != null) {
            id = car.getId();
            brandField.setText(car.getBrand());
            modelField.setText(car.getModel());
            priceField.setText(car.getPrice().toString());
            engineField.setText(car.getEngine());
            if (car.getAvalible())
                avalibleField.setText("Dostępny");
            else
                avalibleField.setText("Niedostępny");
            String url = carService.findUrlByBrandModelEngine(car.getBrand(), car.getModel(), car.getEngine());
            File file = new File(url);
            Image image = new Image(file.toURI().toString(), 450, 200, false, true);
            imageCar.setImage(image);
        }
    }

    public void offForClient() {
        usersButton.setVisible(false);
        insertImageButton.setVisible(false);
        deleteCarButton.setVisible(false);
        clearButton.setVisible(false);
        addCarButton.setVisible(false);
        priceField.setVisible(false);
        modelField.setVisible(false);
        brandField.setVisible(false);
        engineField.setVisible(false);
        avalibleField.setVisible(false);
        brandText.setVisible(false);
        modelText.setVisible(false);
        priceText.setVisible(false);
        avalibleText.setVisible(false);
        engineText.setVisible(false);

        imageCar.setFitWidth(500);
        imageCar.setFitHeight(500);
        imageCar.setX(-320);
        imageCar.setY(-20);


    }

    public void errorClear() {
        errorMessagePrice.setText("");
        errorMessageEngine.setText("");
        errorMessageBrand.setText("");
        errorMessageModel.setText("");
        errorMessageImage.setText("");
    }

    private void borderError(TextField f) {
        f.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
    }

    private void changeTextListener(TextField text, Text error) {
        text.textProperty().addListener((observableValue, s, s2) -> {
            if (!text.getText().equals("")) {
                text.setStyle("-fx-border-width: 0px;");
                error.setText("");
            }
        });
    }


}
