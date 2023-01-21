package com.example.demo1;

import com.example.demo1.helper.Connections;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ConnectionTest {
    @Test
    void connectionDataBaseTest(){
        Connections connections = new Connections();
        assertNotNull(connections.getConnection());
    }
}
