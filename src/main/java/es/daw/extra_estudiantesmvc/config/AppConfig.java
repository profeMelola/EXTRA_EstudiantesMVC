package es.daw.extra_estudiantesmvc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.reactive.function.client.WebClient;

/*
Marca esta clase como una clase de configuración de Spring.
Esto permite que se puedan definir beans (@Bean) dentro de ella que serán gestionados por el contenedor de Spring.
 */

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Value("${api.estudiantes.url}")
    private String apiURL; // para filtros de estudiantes

    @Value("${api.estudiantes.auth.url}")
    private String authURL; // para hacer login en el api y obtener el token

    @Bean
    public WebClient webClientAuth(){
        return WebClient
                .builder()
                .baseUrl(authURL)
                .build();
    }

    @Bean
    //@Primary
    public WebClient webClientAPI(){
        return WebClient
                .builder()
                .baseUrl(apiURL)
                .build();
    }
}
