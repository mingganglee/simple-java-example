package com.swagger_security_oauth_client.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi usersServerOpenApi(@Value("${springdoc.version}") String version,
            @Value("${springdoc.oAuthFlow.tokenUrl}") String tokenUrl) {
        String[] paths = { "/api/v1/**", "/oauth/token" };
        return GroupedOpenApi.builder().group("user_server")
                .addOpenApiCustomiser(openApi -> openApi.info(
                        new Info()
                                .title("授权服务客户端")
                                .description("授权服务客户端描述信息")
                                .version(version)))
                .addOpenApiCustomiser(openApi -> openApi.schemaRequirement(
                        "access_token",
                        new SecurityScheme()
                                .type(Type.OAUTH2)
                                .flows(
                                        new OAuthFlows()
                                                .password(new OAuthFlow().tokenUrl(tokenUrl)))))

                .pathsToMatch(paths)
                .build();
    }
}
