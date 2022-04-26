package icu.gateway.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class WebLog {

    /**
     * id
     */
    private String id;

    /**
     * 操作用户
     */
    private String username;

    /**
     * 操作时间
     */
    private Long startTime;

    /**
     * 消耗时间
     */
    private Integer spendTime;

    /**
     * URI
     */
    private String uri;

    /**
     * 请求类型
     */
    private String method;

    /**
     * 本机地址
     */
    private String localAddress;

    /**
     * 远程地址
     */
    private String remoteAddress;

    /**
     * 请求头
     */
    private String headers;

    /**
     * 请求参数 request body
     */
    private String parameter;

    /**
     * 返回结果 response body
     */
    private String result;

}
