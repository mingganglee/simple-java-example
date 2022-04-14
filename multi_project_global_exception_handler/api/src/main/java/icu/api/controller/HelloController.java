package icu.api.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import icu.api.dto.RegisterParam;

@RestController
public class HelloController {
    
    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterParam registerParam) {
        System.out.println(registerParam);
        return ResponseEntity.ok().body("register success!");
    }
}
