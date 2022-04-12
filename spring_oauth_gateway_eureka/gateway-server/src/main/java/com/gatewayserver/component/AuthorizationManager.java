package com.gatewayserver.component;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext>  {

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext objnect) {
        return Mono.just(new AuthorizationDecision(true));
    }
    
}
