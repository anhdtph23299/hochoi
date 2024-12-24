package com.example.demo.util.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
//http://localhost:8080/swagger-ui/index.html#/
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Info")
                        .version("1.0.0")
                        .description("This is info all API."));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }

//    @Bean
//    public GroupedOpenApi userApi() {
//        return GroupedOpenApi.builder()
//                .group("user")  // Tạo nhóm tài liệu Swagger tên là "user"
//                .pathsToMatch("/api/users/**")  // Bao gồm các endpoint có đường dẫn bắt đầu với /api/users/
//                .build();
//    }
}