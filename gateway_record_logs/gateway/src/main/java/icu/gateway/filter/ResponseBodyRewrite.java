package icu.gateway.filter;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import cn.hutool.json.JSONUtil;
import icu.gateway.domain.WebLog;
import icu.gateway.util.WebLogUtil;
import reactor.core.publisher.Mono;

@Component
public class ResponseBodyRewrite implements RewriteFunction<String, String> {

    private static final Logger logger = LoggerFactory.getLogger(ResponseBodyRewrite.class);

    @Override
    public Publisher<String> apply(ServerWebExchange exchange, String body) {
        body = body == null ? "" : body;
        WebLogUtil.setResult(exchange, body);
        WebLog webLog = WebLogUtil.delWebLog(exchange);
        // path is /login not save log
        if (!exchange.getRequest().getPath().toString().equals("/login")) {
            logger.info(JSONUtil.toJsonStr(webLog));
        }
        return Mono.just(body);
    }
}
