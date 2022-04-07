package com.swagger_security_oauth_client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1000)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().authorizeRequests()
                .antMatchers("/swagger-ui/**", "/user_server/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
