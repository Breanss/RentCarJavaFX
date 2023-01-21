package com.example.demo1.repository;

import com.example.demo1.helper.Connections;
import com.example.demo1.model.Car;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CarRepository {
    Connections connection;

    public CarRepository(){
        connection=new Connections();
    }

    public void insertCar(Car car){
        String sql = "INSERT INTO car VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, null);
            statement.setString(2, car.getBrand());
            statement.setString(3, car.getModel());
            statement.setString(4, car.getEngine());
            statement.setString(5, car.getImage());
            statement.setFloat(6, car.getPrice());
            statement.setBoolean(7,car.getAvalible());
            statement.execute();
        }catch(Exception e){e.printStackTrace();}
    }

    public ObservableList<Car> observeFindAllCar(){
        ObservableList<Car> cars = FXCollections.observableArrayList();
        String sql = "SELECT id, brand, model, engine, avalible, price FROM car";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setBrand(resultSet.getString("brand"));
                car.setModel(resultSet.getString("model"));
                car.setEngine(resultSet.getString("engine"));
                car.setAvalible(resultSet.getBoolean("avalible"));
                car.setPrice(resultSet.getFloat("price"));
                cars.add(car);
            }
        }catch (Exception e){e.printStackTrace();}
        return cars;
    }

    public String findUrlByBrandModelEngine(String b, String m, String en){
        String sql = "select image from car where brand=? and model=? and engine=?";
        String image=null;
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, b);
            statement.setString(2, m);
            statement.setString(3, en);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            image=resultSet.getString("image");
        }catch(Exception e){e.printStackTrace();}
        return image;
    }

    public int isThere(String brand, String model, String engine){
        String sql = "select id from car where brand=? and model=? and engine=?";
        int czy = 0;
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, brand);
            statement.setString(2, model);
            statement.setString(3, engine);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
                czy=resultSet.getInt("id");
        }catch(Exception e){e.printStackTrace();}
        return czy;
    }

    public void deleteById(int id){
        String sql = "DELETE FROM car where id=?";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
        }catch(Exception e){e.printStackTrace();}
    }

    public ObservableList<Car> observeFindlCarWhereAvalibleTrue(){
        ObservableList<Car> cars = FXCollections.observableArrayList();
        String sql = "SELECT id, brand, model, engine, avalible, price FROM car where avalible = 1";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setBrand(resultSet.getString("brand"));
                car.setModel(resultSet.getString("model"));
                car.setEngine(resultSet.getString("engine"));
                car.setAvalible(resultSet.getBoolean("avalible"));
                car.setPrice(resultSet.getFloat("price"));
                cars.add(car);
            }
        }catch (Exception e){e.printStackTrace();}
        return cars;
    }

    public Car findCarByBrandModelEngine(String brand, String model, String engine){
        Car car=new Car();
        String sql = "SELECT * FROM car where brand =? and model=? and engine=?";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, brand);
            statement.setString(2, model);
            statement.setString(3, engine);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                car.setId(resultSet.getInt("id"));
                car.setBrand(resultSet.getString("brand"));
                car.setModel(resultSet.getString("model"));
                car.setEngine(resultSet.getString("engine"));
                car.setImage(resultSet.getString("image"));
                car.setPrice(resultSet.getFloat("price"));
                car.setAvalible(resultSet.getBoolean("avalible"));
            }
        }catch(Exception e){e.printStackTrace();}
        return car;
    }

    public int countCars(){
        String sql = "SELECT count(*)  FROM car";
        int ile=0;
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
                ile=resultSet.getInt("count(*)");
        }catch (Exception e){e.printStackTrace();}
        return ile;
    }
}
