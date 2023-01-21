package com.example.demo1;

import com.example.demo1.validation.RentValidate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentCarValidationTest {
    RentValidate rentValidate = new RentValidate();

    @Test
    void days_should_contain_only_numbers(){
        String days = "1";
        String message="";
        try {
            rentValidate.dayNumber(days);
        }catch (Exception e){
            message=e.getMessage();
        }
        assertEquals("",message);

        days = "1a";
        message="";
        try {
            rentValidate.dayNumber(days);
        }catch (Exception e){
            message=e.getMessage();
        }
        assertEquals("Dni mogą zawierać tylko liczby!",message);
    }
}
