package com.speakingclock.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI SpeakingClockMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Speaking Clock Service")
                        .description("Rest api's to convert time to words representation for speaking clock")
                        .version("1.0"));
    }
}
