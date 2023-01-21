package com.example.demo1;

import com.example.demo1.model.User;
import com.example.demo1.service.UserService;
import com.example.demo1.service.UserServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    UserService userService=new UserServiceImpl();

    @Test
    void add_and_delete_user(){
        User user = new User();
        user.setUserName("test");
        user.setPassword("qwerty123");
        user.setEmail("test@gmail.com");
        user.setAdmin(false);
        userService.insertUser(user);
        User test = userService.findUserByUserName(user.getUserName());
        assertNotNull(test.getId());
        userService.deleteUserByEmail(user.getEmail());
        User test2 = userService.findUserByUserName(user.getUserName());
        assertNull(test2.getId());
    }

    @Test
    void update_user_to_admin(){
        User user = new User();
        user.setUserName("test");
        user.setPassword("qwerty123");
        user.setEmail("test@gmail.com");
        user.setAdmin(false);
        userService.insertUser(user);
        userService.updateUserToAdmin(user.getEmail());
        User test = userService.findUserByEmail(user.getEmail());
        userService.deleteUserByEmail(user.getEmail());
        assertTrue(test.getAdmin());
    }
}
