package es.daw.extra_estudiantesmvc.service;

import es.daw.extra_estudiantesmvc.dto.EstudianteDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FiltrosService {

    // Usar WebClient para conectar al api

    // Primero me autentico para recibir un token

    // Después con el token vuelvo a usar webclient para obtener el informe del estudiante en base al filtro


    private final AuthService authService;

    public EstudianteDTO filtrar(String url){
        String token = authService.obtenerToken();
        log.info("*******************");
        log.info(token);
        log.info("*******************");

        return null;
    }



}
