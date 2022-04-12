package com.gatewayserver.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// import lombok.Data;
// import lombok.EqualsAndHashCode;

// @Data
// @EqualsAndHashCode(callSuper = false)
@Component
@ConfigurationProperties(prefix = "secure.ignore")
public class IgnoreUrlsConfig {
    private List<String> urls;

    public List<String> getUrls() {
        return this.urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

}
