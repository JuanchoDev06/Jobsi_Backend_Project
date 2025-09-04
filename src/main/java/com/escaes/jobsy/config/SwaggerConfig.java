package com.escaes.jobsy.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Jobsi API")
                        .version("1.0")
                        .description("Plataforma web de micro-trabajos para los estudiantes del PCJIC. "
                                + "Este sistema permite la publicación, gestión y asignación de tareas con registro seguro "
                                + "y validación administrativa, optimizando el uso del tiempo libre, "
                                + "fortaleciendo la economía estudiantil y fomentando la ayuda mutua dentro de la comunidad universitaria."));
    }
}
