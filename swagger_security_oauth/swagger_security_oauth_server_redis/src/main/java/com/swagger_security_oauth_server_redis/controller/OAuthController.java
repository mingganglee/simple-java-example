package com.swagger_security_oauth_server_redis.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "oauth2 控制类")
@RestController
@RequestMapping(value = "/oauth")
@SecurityRequirement(name = "access_token")
public class OAuthController {

    @Autowired
    private TokenStore tokenStore;

    @RequestMapping(value = "/revoke", method = RequestMethod.GET)
    public void logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(accessToken);
        }
    }

    @GetMapping("/me")
    public Object getCurrentUser(Principal principal) {
        return principal;
    }

}
