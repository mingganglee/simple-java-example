package icu.gateway.filter;



import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import icu.gateway.util.WebLogUtil;
import reactor.core.publisher.Mono;

@Component
public class RequestBodyRewrite implements RewriteFunction<String, String> {

    @Override
    public Publisher<String> apply(ServerWebExchange exchange, String body) {

        // You can get username from jwt
        String username = "tony";
        WebLogUtil.setUsername(exchange, username);

        body = body == null ? "" : body;
        WebLogUtil.setParameter(exchange, body);
        return Mono.just(body);

    }
}