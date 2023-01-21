package com.example.demo1;

import com.example.demo1.model.Car;
import com.example.demo1.service.CarService;
import com.example.demo1.service.CarServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarServiceTest {
    CarService carService = new CarServiceImpl();

    @Test
    void add_delete_car(){
        Car car = new Car();
        car.setBrand("Test");
        car.setModel("test");
        car.setEngine("5.0");
        car.setPrice(200F);
        car.setAvalible(true);
        car.setImage("src/main/resources/com/example/demo1/img/audi.jpg");

        carService.insertCar(car);
        Car test = carService.findCarByBrandModelEngine(car.getBrand(),car.getModel(),car.getEngine());
        assertNotNull(test);
        carService.deleteById(test.getId());
        Car test2 = carService.findCarByBrandModelEngine(car.getBrand(),car.getModel(),car.getEngine());
        assertNull(test2.getId());
    }

    @Test
    void count_cars(){
        Car car = new Car();
        car.setBrand("Test");
        car.setModel("test");
        car.setEngine("5.0");
        car.setPrice(200F);
        car.setAvalible(true);
        car.setImage("src/main/resources/com/example/demo1/img/audi.jpg");
        int count = carService.countCars();
        carService.insertCar(car);
        int count2 = carService.countCars();
        Car test = carService.findCarByBrandModelEngine(car.getBrand(),car.getModel(),car.getEngine());
        carService.deleteById(test.getId());
        int sum = count2-count;
        assertEquals(1, sum);
    }
}
