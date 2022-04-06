package com.user.controller;

import com.library.UserModel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping(value = "/hello")
    public String hello() {
        return "hello user.";
    }

    @GetMapping(value = "/")
    public UserModel getUser() {
        return new UserModel(1, "zhangsan", 18);
    }

}
