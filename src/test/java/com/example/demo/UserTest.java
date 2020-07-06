package com.example.demo;

import com.example.demo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {
    @Autowired
    UserServiceImpl userService;


    @Test
    public void testLog(){
        System.out.println(userService.queryUserByName("zhangsan"));
    }
}
