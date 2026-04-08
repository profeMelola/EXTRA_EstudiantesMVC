package es.daw.extra_estudiantesmvc.service;

import es.daw.extra_estudiantesmvc.dto.EstudianteDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class FiltrosService {

    // -------------------------------------------------------
    // OPCIONES A LA HORA DE INYECTAR UN BEAN
    // Opción 1. Resuelve por nombre del método
    private final WebClient webClientAPI;

    // Opción 2. Indicar el  nombre con Qualifier
//    @Qualifier("webClientAPI")
//    private final WebClient webClient;

    // Opción 3. Inyecta el que tiene la etiqueta @Primary en el @Bean
    //private final WebClient webClient;

    // Inyección como parámetro
//    @Autowired
//    private WebClient clienteByParam;

    // ---------------------------------------------


    private final AuthService authService;


    // ----------------- INTERFAZ FUNCIONAL ------------------------
    /*
    Es una interfaz funcional de Java (java.util.function.Function) cuyo único método abstracto es apply.
    Tipado: toma un UriBuilder y devuelve un URI.
    En la práctica significa:
    “dame una función que, recibiendo un UriBuilder, construya y devuelva la URI final que debe usarse en la petición HTTP”.

    Permite construir URIs dinámicos (path variables, query params, encoding) de forma segura y programática.
    Evita concatenaciones manuales de strings y errores de encoding.
    Facilita reutilizar el service con distintos parámetros sin tener que pasar Strings ya concatenadas.
     */
    public EstudianteDTO filtrar(Function<UriBuilder, URI> uriFn){
    //public EstudianteDTO filtrar(String url){

        String token = authService.obtenerToken();
        log.info("*******************");
        log.info("Token obtenido (oculto en INFO): **** (longitud={})", token != null ? token.length() : 0);
        log.info("*******************");


        return webClientAPI
                //.get()
                .method(HttpMethod.GET)
                .uri(uriFn)
                .header("Authorization","Bearer "+token)
                .retrieve()
                // PENDIENTE REVISAR LA GESTIÓN DE ERROR AL CONECTAR AL API
                .onStatus(
                        status -> status.isError(),
                        clientResponse -> {
                            log.error("Error Http al llamar al endpoint: {}",clientResponse.statusCode());
                            return clientResponse
                                    .bodyToMono(String.class).defaultIfEmpty("")
                                    .flatMap(errorBody -> Mono.error(() ->
                                            new RuntimeException("Error Http al llamar al endpoint: "+errorBody)));
                        }
                )
                .bodyToMono(EstudianteDTO.class)
                .block();
    }

//    public EstudianteDTO filtrarConString(String nia,String nombre, String apellido1, String apellido2){
//        String token = authService.obtenerToken();
//        log.info("*******************");
//        log.info("Token obtenido (oculto en INFO): **** (longitud={})", token != null ? token.length() : 0);
//        log.info("*******************");
//
//        if (nia != null) {
//            return webClientAPI
//                    .method(HttpMethod.GET)
//                    .uri(uriBuilder -> uriBuilder
//                            .path("/findByNia")
//                            .queryParam("nia", nia)
//                            .build())
//                    .header("Authorization", "Bearer " + token)
//                    .retrieve()
//                    .bodyToMono(EstudianteDTO.class)
//                    .block();
//        }
//        //else ....
//
//    }



//    public EstudianteDTO findByNia(String nia) {
//        String token = authService.obtenerToken();
//
//        return webClientAPI
//                .method(HttpMethod.GET)
//                .uri(uriBuilder -> uriBuilder
//                        .path("/estudiantes/search/findByNia")
//                        .queryParam("nia", nia)
//                        .build())
//                .header("Authorization", "Bearer " + token)
//                .retrieve()
//                .bodyToMono(EstudianteDTO.class)
//                .block();
//    }

}
