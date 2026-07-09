package com.yiseth.productapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI productApi() {

        return new OpenAPI()

                .info(new Info()

                        .title("Product API")

                        .description("API REST para la gestión de productos")

                        .version("1.0")

                        .contact(new Contact()

                                .name("Yerssi21")

                                .email("yerssiyisetho@gmail.com"))

                        .license(new License()

                                .name("MIT")

                                .url("https://opensource.org/licenses/MIT")))

                .externalDocs(new ExternalDocumentation()

                        .description("Repositorio del proyecto")

                        .url("https://github.com/"));
    }

}