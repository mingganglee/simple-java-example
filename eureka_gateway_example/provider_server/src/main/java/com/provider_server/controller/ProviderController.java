package com.provider_server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/provider")
public class ProviderController {
    
    @GetMapping("/hello")
    public String get_hello() {
        return "hello get provider";
    }

    @PostMapping("/hello")
    public String post_hello() {
        return "hello post provider";
    }
}
