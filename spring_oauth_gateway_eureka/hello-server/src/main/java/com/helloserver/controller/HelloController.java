package com.helloserver.controller;

import com.helloserver.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {
    
    @Autowired
    UserService userService;

    @GetMapping(value = "/hello")
    public String getHello() {
        return "hello hello server.";
    }

    @GetMapping(value = "/user")
    public String helloUser() {
        String userHello = userService.getUserHello();
        return userHello + " --- helloUser api.";
    }
}
