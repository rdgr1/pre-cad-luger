package br.com.luger.dev.precad.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openapi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Luger API")
                        .version("V1")
                        .description("REST API")

                );

    }

}