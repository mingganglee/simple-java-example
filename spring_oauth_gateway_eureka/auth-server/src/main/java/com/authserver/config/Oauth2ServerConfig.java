package com.authserver.config;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

import com.authserver.component.JwtTokenEnhancer;
import com.authserver.service.impl.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;
    // @Autowired
    // JwtAccessTokenConverter jwtAccessTokenConverter;
    // @Autowired
    // private JwtTokenEnhancer jwtTokenEnhancer;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        // 设置 JWT 增强内容
        TokenEnhancerChain chain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(new JwtTokenEnhancer());
        delegates.add(jwtAccessTokenConverter());
        chain.setTokenEnhancers(delegates);

        // jwt store
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsServiceImpl)
                // accessToken 转成 JWTToken
                // .tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter())
                .tokenEnhancer(chain);
        
        // redis store
        // endpoints.authenticationManager(authenticationManager)
        //         .userDetailsService(userDetailsServiceImpl)
        //         // .pathMapping("/oauth/check_token", "/my/oauth/check_token")
        //         // accessToken 转成 JWTToken
        //         .tokenStore(rediTokenStore);

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // 客户端 ID
                .withClient("client")
                // 秘钥
                .secret(passwordEncoder.encode("client"))
                // 重定向地址
                // .redirectUris("http://localhost:8081/login")
                // 授权范围
                .scopes("all")
                // accessToken 过期时间 单位: 秒
                .accessTokenValiditySeconds(3600)
                //refreshToken 过期时间 单位: 秒
                .refreshTokenValiditySeconds(86400)
                // .autoApprove(true)
                /**
                 * 授权类型
                 * authorization_code: 授权码模式
                 * password: 密码模式
                 * refresh_token: 刷新令牌
                 */
                .authorizedGrantTypes("password", "refresh_token");
                // .authorizedGrantTypes("authorization_code", "password");
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }

    @Bean
    public KeyPair keyPair() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "client".toCharArray());
        return keyStoreKeyFactory.getKeyPair("jwt", "client".toCharArray());
    }


}
