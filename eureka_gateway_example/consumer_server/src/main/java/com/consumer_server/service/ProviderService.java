package com.consumer_server.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@FeignClient(value = "provider-server", path = "/provider")
public interface ProviderService {
    
    @GetMapping(value = "/hello")
    String get_hello();

    @PostMapping(value = "/hello")
    String post_hello();
}
