package com.userserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserServer {
    
    @GetMapping(value = "/hello")
    public String getHello() {
        return "hello user server.";
    }
}
