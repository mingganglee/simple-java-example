package icu.gateway.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import icu.gateway.domain.WebLog;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

public class WebLogUtil {

    public static Map<String, WebLog> logContainer = new HashMap<>();

    /**
     * 初始化 WebLog 对象
     * 
     * @param exchange
     * @return
     */
    public static WebLog init(ServerWebExchange exchange) {
        WebLog webLog = new WebLog();
        ServerHttpRequest request = exchange.getRequest();

        webLog.setStartTime(System.currentTimeMillis());
        webLog.setId(request.getId());
        webLog.setMethod(request.getMethodValue());
        webLog.setUri(request.getURI().toString());
        webLog.setLocalAddress(request.getLocalAddress().getHostName());
        webLog.setRemoteAddress(request.getRemoteAddress().getHostName());
        webLog.setHeaders(request.getHeaders().toString());

        logContainer.put(request.getId(), webLog);
        return webLog;
    }

    /**
     * 获取 WebLog 对象, 如果没有则初始化
     * 
     * @param exchange
     * @return
     */
    public static WebLog getWebLog(ServerWebExchange exchange) {
        String logId = exchange.getRequest().getId();
        if (logContainer.containsKey(logId)) {
            return logContainer.get(logId);
        } else {
            return init(exchange);
        }
    }

    /**
     * 写入日志, 并删除 WebLog 对象
     * 
     * @param exchange
     * @return
     */
    public static WebLog delWebLog(ServerWebExchange exchange) {
        return logContainer.remove(exchange.getRequest().getId());
    }

    /**
     * 写入用户名
     * 
     * @param exchange
     * @param username
     */
    public static void setUsername(ServerWebExchange exchange, String username) {

        WebLog webLog = getWebLog(exchange);
        webLog.setUsername(username);
    }

    /**
     * 写入 request body
     * 
     * @param exchange
     * @param parameter
     */
    public static void setParameter(ServerWebExchange exchange, String parameter) {
        if (parameter != null && !parameter.isEmpty()) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(parameter);
            parameter = m.replaceAll("");
        }

        WebLog webLog = getWebLog(exchange);
        webLog.setParameter(parameter);
    }

    /**
     * 写入 response body, 计算运行时间, 并写入日志
     * 
     * @param exchange
     * @param result
     */
    public static void setResult(ServerWebExchange exchange, String result) {
        WebLog webLog = getWebLog(exchange);
        webLog.setResult(result);
        webLog.setSpendTime((int) (System.currentTimeMillis() - webLog.getStartTime()));
    }

}
