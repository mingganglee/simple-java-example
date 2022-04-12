package com.helloserver.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@FeignClient(value = "user-server")
public interface UserService {
    
    @GetMapping("/user/hello")
    String getUserHello();
}
