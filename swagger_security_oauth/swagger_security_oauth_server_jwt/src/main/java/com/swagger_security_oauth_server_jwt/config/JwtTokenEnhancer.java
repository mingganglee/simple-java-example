package com.swagger_security_oauth_server_jwt.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class JwtTokenEnhancer implements TokenEnhancer {

    /**
     * Jwt Token 增加自定义内容, 格式 Map<String, Object>
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> map = new HashMap<>();
        // Jwt Token 增加自定义内容, 示例
        map.put("enhance", "enhancer info");
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);
        return accessToken;
    }
    
}
