package com.authserver.component;

import java.util.HashMap;
import java.util.Map;

import com.authserver.pojo.SecurityUser;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

// @Component
public class JwtTokenEnhancer implements TokenEnhancer {

    /**
     * Jwt Token 增加自定义内容, 格式 Map<String, Object>
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        Map<String, Object> map = new HashMap<>();
        // Jwt Token 增加自定义内容, 示例
        map.put("id", securityUser.getId());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);
        return accessToken;
    }
    
}
