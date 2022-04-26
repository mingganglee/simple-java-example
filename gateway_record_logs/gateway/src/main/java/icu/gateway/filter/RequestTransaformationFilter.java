package icu.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyRequestBodyGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class RequestTransaformationFilter implements GlobalFilter, Ordered {

    @Autowired
    private ModifyRequestBodyGatewayFilterFactory modifyRequestBodyFilter;
    @Autowired
    private RequestBodyRewrite requestBodyRewrite;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return modifyRequestBodyFilter
                .apply(
                        new ModifyRequestBodyGatewayFilterFactory.Config()
                                .setRewriteFunction(String.class, String.class, requestBodyRewrite))
                .filter(exchange, chain);
    }

    @Override
    public int getOrder() {
        return NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER - 1;
    }

}
