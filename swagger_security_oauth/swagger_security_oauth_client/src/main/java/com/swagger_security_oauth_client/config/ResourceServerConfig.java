package com.swagger_security_oauth_client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().authorizeRequests()
                .antMatchers("/swagger-ui/**", "/user_server/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .requestMatchers()
                .antMatchers("/api/v1/**");
    }
}
