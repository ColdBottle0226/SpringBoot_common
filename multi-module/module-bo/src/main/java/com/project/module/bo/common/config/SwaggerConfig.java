package com.project.module.bo.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

        @Bean
        public OpenAPI openAPI() {
            Info info = new Info()
                    .title("데모 프로젝트 API Document")
                    .version("v0.0.1")
                    .description("데모 프로젝트의 API 명세서입니다.");

            return new OpenAPI()
                    .components(new Components())
                    .addSecurityItem(new SecurityRequirement().addList("Authentication"))
                    .components(new Components()
                            .addSecuritySchemes("Authentication",
                                    new io.swagger.v3.oas.models.security.SecurityScheme()
                                            .type(SecurityScheme.Type.HTTP)
                                            .scheme("bearer")
                                            .bearerFormat("JWT")))
                    .info(info);

        }
}

