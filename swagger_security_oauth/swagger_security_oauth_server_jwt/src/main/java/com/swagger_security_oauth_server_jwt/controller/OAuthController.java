package com.swagger_security_oauth_server_jwt.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "oauth2 控制类")
@RestController
@RequestMapping(value = "/oauth")
@SecurityRequirement(name = "access_token")
public class OAuthController {

    @GetMapping("/me")
    public Object getCurrentUser(Principal principal) {
        return principal;
    }

}
