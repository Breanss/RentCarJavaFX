package com.example.demo1;

import com.example.demo1.helper.Encoder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EncoderPasswordTest {
    @Test
    void encode_password_test() {
        String password = "qwerty123";
        String encoded = Encoder.encodePassword(password);
        assertNotEquals(encoded, password);
    }
}
