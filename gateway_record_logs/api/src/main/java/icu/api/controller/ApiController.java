package icu.api.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class ApiController {
    
    @GetMapping(value = "/hello")
    public String getHello() {
        return "get hello world.";
    }

    @PostMapping(value = "/hello")
    public String postHello(@RequestParam("name") String name) {
        return String.format("post hello %s.", name);
    }

    @PutMapping(value = "/hello")
    public String putHello(@RequestParam("name") String name) {
        return String.format("put hello %s.", name);
    }

    @DeleteMapping(value = "/hello")
    public String delHello(@RequestParam("name") String name) {
        return String.format("del hello %s.", name);
    }
}
