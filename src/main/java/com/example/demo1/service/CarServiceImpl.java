package com.example.demo1.service;

import com.example.demo1.model.Car;
import com.example.demo1.repository.CarRepository;
import javafx.collections.ObservableList;

public class CarServiceImpl implements CarService {
    private final CarRepository carRepository = new CarRepository();

    @Override
    public void insertCar(Car car) {
        carRepository.insertCar(car);
    }

    @Override
    public ObservableList<Car> observeFindAllCar() {
        return carRepository.observeFindAllCar();
    }

    @Override
    public String findUrlByBrandModelEngine(String brand, String model, String engine) {
        return carRepository.findUrlByBrandModelEngine(brand, model, engine);
    }

    @Override
    public int isThere(String brand, String model, String engine) {
        return carRepository.isThere(brand, model, engine);
    }

    @Override
    public void deleteById(int id) {
        carRepository.deleteById(id);
    }

    @Override
    public Car findCarByBrandModelEngine(String brand, String model, String engine) {
        return carRepository.findCarByBrandModelEngine(brand, model, engine);
    }

    @Override
    public ObservableList<Car> observeFindlCarWhereAvalibleTrue() {
        return carRepository.observeFindlCarWhereAvalibleTrue();
    }

    @Override
    public int countCars() {
        return carRepository.countCars();
    }
}
