package es.daw.extra_estudiantesmvc.service;

import es.daw.extra_estudiantesmvc.dto.auth.AuthRequest;
import es.daw.extra_estudiantesmvc.dto.auth.AuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final WebClient webClientAuth;

    @Value("${api.estudiantes.auth.login}")
    private String login;
    @Value("${api.estudiantes.auth.password}")
    private String password;

    // ----------------------
    // PENDIENTE!!! MEJORA!!!
    // 1. Pido un token nuevo si no lo tengo y si lo uso y da error de caducidad pido otro..
    // 2. Controlar Conexión rehusada y dar un error exacto.
    // ---------------

    public String obtenerToken(){

        AuthRequest  authRequest = new AuthRequest(login, password);

        AuthResponse authResponse = webClientAuth
                //.post()
                .method(HttpMethod.POST)
                .header("Content-Type","application/json")
                .bodyValue(authRequest)
                .retrieve()
                .bodyToMono(AuthResponse.class)
                .block();

        // pendiente!!!!! .onStatus y lanzar ConnectionApiRestException....
        return authResponse !=null ? authResponse.token():"";

    }


}
