package br.com.techchallenge.fiap.techChallenge.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI getOpenAPI() {
        return new OpenAPI().info(
                new Info()
                        .title("Fiap Tech Challenge API")
                        .description("API para cadastrar clientes e donos de restaurantes")
                        .version("0.1.0")
                        .license(new License().name("Apache 2.0").url("http://github.com/Lucas-Duarte-94"))
        );
    }
}
