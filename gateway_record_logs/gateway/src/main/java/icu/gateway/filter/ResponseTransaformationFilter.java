package icu.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyResponseBodyGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class ResponseTransaformationFilter implements GlobalFilter, Ordered {

    @Autowired
    private ModifyResponseBodyGatewayFilterFactory modifyResponseBodyGatewayFilterFactory;
    @Autowired
    private ResponseBodyRewrite responseBodyRewrite;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return modifyResponseBodyGatewayFilterFactory
                .apply(
                        new ModifyResponseBodyGatewayFilterFactory.Config()
                                .setRewriteFunction(String.class, String.class, responseBodyRewrite))
                .filter(exchange, chain);
    }

    @Override
    public int getOrder() {
        return NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER - 1;
    }
}
