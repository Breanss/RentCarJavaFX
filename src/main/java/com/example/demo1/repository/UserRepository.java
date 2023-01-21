package com.example.demo1.repository;

import com.example.demo1.helper.Connections;
import com.example.demo1.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class UserRepository {
    Connections connection;

    public UserRepository(){
        connection=new Connections();
    }

    public User findUserByUsernameAndPassword(String userName, String password){
        User user = new User();
        String sql = "SELECT * FROM user where username = ? and password = ?";
        Base64.Encoder encoder = Base64.getEncoder();
        String encoded = encoder.encodeToString(password.getBytes());
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, userName);
            statement.setString(2, encoded);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
               user.setId(resultSet.getInt("id"));
               user.setUserName(resultSet.getString("username"));
               user.setPassword(resultSet.getString("password"));
               user.setEmail(resultSet.getString("email"));
               user.setAdmin(resultSet.getBoolean("admin"));
            }
        }catch (Exception e){e.printStackTrace();}
        return user;
    }

    public void insertUser(User user){
        String sql = "INSERT INTO user VALUES (?,?,?,?,?)";

        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, null);
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getEmail());
            statement.setBoolean(5, false);
            statement.execute();
        }catch(Exception e){e.printStackTrace();}
    }

    public User findUserByUsername(String userName){
        User user = new User();
        String sql = "SELECT * FROM user where username = ?";

        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setAdmin(resultSet.getBoolean("admin"));
            }
        }catch (Exception e){e.printStackTrace();}
        return user;
    }

    public User findUserByEmail(String email){
        User user = new User();
        String sql = "SELECT * FROM user where email = ?";

        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setAdmin(resultSet.getBoolean("admin"));
            }
        }catch (Exception e){e.printStackTrace();}
        return user;
    }

    public List<User> findAllUser(){
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setAdmin(resultSet.getBoolean("admin"));
                users.add(user);
            }
        }catch (Exception e){e.printStackTrace();}
        return users;
    }

    public ObservableList<User> observeFindAllUserNoAdmin(){
        int sum = 1;
        ObservableList<User> users = FXCollections.observableArrayList();;
        String sql = "SELECT id, username, email, admin FROM user";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                User user = new User();
                if(!resultSet.getBoolean("admin")) {
                    user.setId(sum);
                    user.setUserName(resultSet.getString("username"));
                    user.setEmail(resultSet.getString("email"));
                    users.add(user);
                    sum++;
                }
            }
        }catch (Exception e){e.printStackTrace();}
        return users;
    }

    public void updateUserToAdmin(String email){
        String sql = "UPDATE user SET admin=1 WHERE email=?";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, email);
            statement.execute();
        }catch (Exception e){e.printStackTrace();}
    }

    public  void deleteUserByEmail(String email){
        String sql = "DELETE from user WHERE email=?";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, email);
            statement.execute();
        }catch (Exception e){e.printStackTrace();}
    }
}
