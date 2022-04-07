package com.swagger_security_oauth_client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Users 控制类")
@RestController
@RequestMapping(value = "/api/v1")
@SecurityRequirement(name = "access_token")
public class UserController {

    @GetMapping(value = "/hello")
    public String hello() {
        return "hello swagger security oauth client.";
    }
}