package com.authserver.service;

// import com.buoyauth.dto.UserinfoDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "buoy-user")
public interface UserService {
    // @GetMapping(value = "/user/")
    // UserinfoDTO findByUsername(@RequestParam("username") String username);
}
