package com.example.demo1.helper;

import java.sql.DriverManager;
import java.sql.Connection;

public class Connections {
    Connection connection;

    public Connections() {
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentcar", "root","");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public java.sql.Connection getConnection() {
        return connection;
    }
}
