package com.kaka.controller;

import com.kaka.entity.sys.User;
import com.kaka.service.mapper.sys.UserMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KakaControllerApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    void contextLoads() {
//        User user = userMapper.findUserById(1L);
        User user = userMapper.findUserByUserNameOrEmail("Kamisora");
        System.out.println(user.getUserName());
    }
}
