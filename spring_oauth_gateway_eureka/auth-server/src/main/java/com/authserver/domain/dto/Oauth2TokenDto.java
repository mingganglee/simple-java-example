package com.authserver.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Builder
public class Oauth2TokenDto {
    private String token;
    private String refreshToken;
    private String tokenHead;
    private int expiresIn;
}
