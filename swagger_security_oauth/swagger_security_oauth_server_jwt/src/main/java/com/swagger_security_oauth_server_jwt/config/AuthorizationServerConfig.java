package com.swagger_security_oauth_server_jwt.config;

import java.util.ArrayList;
import java.util.List;

import com.swagger_security_oauth_server_jwt.service.impl.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    @Qualifier("jwtTokenStore")
    private TokenStore tokenStore;
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    @Autowired
    private JwtTokenEnhancer jwtTokenEnhancer;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        // 设置 JWT 增强内容
        TokenEnhancerChain chain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer);
        delegates.add(jwtAccessTokenConverter);
        chain.setTokenEnhancers(delegates);

        // jwt store
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsServiceImpl)
                // accessToken 转成 JWTToken
                .tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenEnhancer(chain);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // 客户端 ID
                .withClient("client")
                // 秘钥
                .secret(passwordEncoder.encode("client"))
                // 重定向地址
                .redirectUris("http://localhost:8081/login")
                // 授权范围
                .scopes("all")
                // accessToken 过期时间 单位: 秒
                .accessTokenValiditySeconds(3600)
                //refreshToken 过期时间 单位: 秒
                // .refreshTokenValiditySeconds(86400)
                .autoApprove(true)
                /**
                 * 授权类型
                 * authorization_code: 授权码模式
                 * password: 密码模式
                 * refresh_token: 刷新令牌
                 */
                // .authorizedGrantTypes("authorization_code", "password", "refresh_token");
                .authorizedGrantTypes("authorization_code", "password");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
        .checkTokenAccess("isAuthenticated()");

        // 解决 /oauth/token cors 跨域问题
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.applyPermitDefaultValues();

        // add allow-origin to the headers
        config.addAllowedHeader("access-control-allow-origin");

        source.registerCorsConfiguration("/oauth/token", config);
        CorsFilter filter = new CorsFilter(source);
        security.addTokenEndpointAuthenticationFilter(filter);
    }
}
