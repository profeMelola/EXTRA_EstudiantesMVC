package es.daw.extra_estudiantesmvc.service;

import es.daw.extra_estudiantesmvc.dto.EstudianteDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class FiltrosService {

    private final WebClient webClientAPI;

    private final AuthService authService;

    public EstudianteDTO filtrar(Function<UriBuilder, URI> uriFn){
    //public EstudianteDTO filtrar(String url){

        String token = authService.obtenerToken();
        log.info("*******************");
        log.info("Token obtenido (oculto en INFO): **** (longitud={})", token != null ? token.length() : 0);
        log.info("URL de consulta: {}", uriFn.toString());
        log.info("*******************");


        return webClientAPI.get()
                .uri(uriFn)
                .header("Authorization","Bearer "+token)
                .retrieve()
                .bodyToMono(EstudianteDTO.class)
                .block();
    }


    public EstudianteDTO findByNia(String nia) {
        String token = authService.obtenerToken();

        return webClientAPI.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/estudiantes/search/findByNia")
                        .queryParam("nia", nia)
                        .build())
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(EstudianteDTO.class)
                .block();
    }

}
