package com.example.demo1.service;

import com.example.demo1.model.Car;
import javafx.collections.ObservableList;

public interface CarService {
    void insertCar(Car car);
    ObservableList<Car> observeFindAllCar();
    String findUrlByBrandModelEngine(String brand, String model, String engine);
    int isThere(String brand, String model, String engine);
    void deleteById(int id);
    ObservableList<Car> observeFindlCarWhereAvalibleTrue();
    int countCars();
}
