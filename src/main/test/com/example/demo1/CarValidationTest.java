package com.example.demo1;

import com.example.demo1.model.Car;
import com.example.demo1.validation.AddCarValidate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarValidationTest {
    Car car;
    AddCarValidate addCarValidate = new AddCarValidate();

    @Test
    void correct_brand_name() {
        car = new Car();
        String message = "";
        car.setBrand("Audi");

        try {
            addCarValidate.brand(car.getBrand());
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("", message);
    }

    @Test
    void the_brand_should_have_at_least_2_characters() {
        car = new Car();
        String message = "";

        car.setBrand("s");

        try {
            addCarValidate.brand(car.getBrand());
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Marka powinna zawierać minimum 2 znaki!", message);
    }

    @Test
    void the_brand_should_only_contain_letters() {
        car = new Car();
        car.setBrand("Audi*");

        String message = "";

        try {
            addCarValidate.brand(car.getBrand());
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Marka może zawierać tylko litery!", message);
    }

    @Test
    void the_brand_can_have_maximum_of_20_characters() {
        car = new Car();
        car.setBrand("Aaaaaaaaaaaaaaaaaaaaaa");

        String message = "";

        try {
            addCarValidate.brand(car.getBrand());
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Marka może mieć maximum 20 znaków!", message);
    }

    @Test
    void correct_model_name() {
        car = new Car();
        String message = "";
        car.setModel("Rs");

        try {
            addCarValidate.model(car.getModel());
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("", message);
    }

    @Test
    void the_model_should_have_at_least_2_characters() {
        car = new Car();
        String message = "";

        car.setModel("s");

        try {
            addCarValidate.model(car.getModel());
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Model powinien zawierać minimum 2 znaki!", message);
    }

    @Test
    void the_model_should_only_contain_letters() {
        car = new Car();
        car.setModel("Rs1");

        String message = "";

        try {
            addCarValidate.model(car.getModel());
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Model może zawierać tylko litery!", message);
    }

    @Test
    void the_model_can_have_maximum_of_20_characters() {
        car = new Car();
        car.setModel("Aaaaaaaaaaaaaaaaaaaaaa");

        String message = "";

        try {
            addCarValidate.model(car.getModel());
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Model może mieź maximum 20 znaków!", message);
    }

    @Test
    void correct_engine() {
        car = new Car();
        String message = "";
        car.setEngine("5.0");

        try {
            addCarValidate.engine(car.getEngine());
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("", message);
    }

    @Test
    void the_engine_should_have_at_least_1_characters() {
        car = new Car();
        String message = "";

        car.setEngine("");

        try {
            addCarValidate.engine(car.getEngine());
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Silnik powinien zawierać minimum 1 znak!", message);
    }

    @Test
    void the_engine_can_have_maximum_of_20_characters() {
        car = new Car();
        car.setEngine("Aaaaaaaaaaaaaaaaaaaaaa");

        String message = "";

        try {
            addCarValidate.engine(car.getEngine());
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Silnik może mieź maximum 20 znaków!", message);
    }

    @Test
    void correct_price() {
        car = new Car();
        String message = "";
        car.setPrice(55F);

        try {
            addCarValidate.price(car.getPrice().toString());
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("", message);
    }

    @Test
    void the_price_must_be_greater_than_0() {
        car = new Car();
        String message = "";
        car.setPrice(0F);

        try {
            addCarValidate.price(car.getPrice().toString());
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Cena musi być większa od 0!", message);

    }

    @Test
    void the_image_must_be_imported(){
        car = new Car();
        String message = "";
        car.setImage("");

        try {
            addCarValidate.image(car.getImage());
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Index -1 out of bounds for length 1", message);
    }
}
