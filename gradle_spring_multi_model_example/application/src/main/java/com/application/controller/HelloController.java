package com.application.controller;

import com.library.UserModel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class HelloController {
    
    @GetMapping(value = "/hello")
    public String hello() {
        return "hello application.";
    }

    @GetMapping(value = "/")
    public UserModel getUser() {
        return new UserModel(1, "lisi", 20);
    }
}
