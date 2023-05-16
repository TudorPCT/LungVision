package com.lungvision.lungdiseasexrayclassificationbe.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI openAPIDefinition() {
        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .info(new io.swagger.v3.oas.models.info.Info().title("LungVision API")
                        .description("Rest API")
                        .version("v1.0"));
    }
}
