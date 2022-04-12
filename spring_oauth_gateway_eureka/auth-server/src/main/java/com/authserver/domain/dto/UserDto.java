package com.authserver.domain.dto;

import java.util.List;

public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Integer status;
    private List<String> roles;
}
