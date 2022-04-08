package com.consumer_server.controller;

import com.consumer_server.service.ProviderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/consumer")
public class ConsumerController {
    
    @Autowired
    private ProviderService providerService;

    @GetMapping("/hello")
    public String getHello() {
        String getHelloString = providerService.get_hello();
        String postHelloString = providerService.post_hello();

        return getHelloString + " --- " + postHelloString;
    }
}
