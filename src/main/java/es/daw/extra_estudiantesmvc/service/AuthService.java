package es.daw.extra_estudiantesmvc.service;

import es.daw.extra_estudiantesmvc.dto.auth.AuthRequest;
import es.daw.extra_estudiantesmvc.dto.auth.AuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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

    public String obtenerToken(){

        AuthRequest  authRequest = new AuthRequest(login, password);

        AuthResponse authResponse = webClientAuth
                .post()
                .header("Content-Type","application/json")
                .bodyValue(authRequest)
                .retrieve()
                .bodyToMono(AuthResponse.class)
                .block();

        // pendiente!!!!! .onStatus y lanzar ConnectionApiRestException....
        return authResponse !=null ? authResponse.token():"";

    }


}
