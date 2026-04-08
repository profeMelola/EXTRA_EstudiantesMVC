package es.daw.extra_estudiantesmvc.controller;

import es.daw.extra_estudiantesmvc.dto.EstudianteDTO;
import es.daw.extra_estudiantesmvc.service.FiltrosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class FiltrosController {

    private final FiltrosService filtrosService;

    @GetMapping("/filtrar")
    public String filtrar(
            @RequestParam(required = false) String nia,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String primerApellido,
            @RequestParam(required = false) String segundoApellido,
            Model model) {

//        https://github.com/profeMelola/DWES-02-2025-26/blob/main/APOYO_TEORIA/Interfaces%20funcionales%20y%20lambdas.md
//        Function<A, B> es una interfaz funcional de Java que representa "dado un A, devuelve un B".
//                Tiene un único método abstracto: B apply(A a);

        // http://localhost:8082/estudiantes/search

        // BENEFICIOS USAR URIBUILDER VS STRING
        // Enconding automático
        // Nulos controlados. No hace falta comprobar si es nulo
        // Se lee muy fácil
        // Parámetros dinámicos fácil
        // Integración nativa con WebClient


        EstudianteDTO estudianteDTO = null;

        if (nia != null){
            estudianteDTO = filtrosService.filtrar(
                    uriBuilder -> uriBuilder
                            .path("/findByNia")
                            .queryParam("nia",nia)
                            .build());

            //filtrosService.filtrarConString(nia,null,null,null);

        }else if (nombre != null && primerApellido != null && segundoApellido != null){
            estudianteDTO = filtrosService.filtrar(uriBuilder -> uriBuilder
                    .path("/findByNombreAndPrimerApellidoAndSegundoApellido")
                    .queryParam("nombre",nombre)
                    .queryParam("primerApellido",primerApellido)
                    .queryParam("segundoApellido",segundoApellido)
                    .build());

            //filtrosService.filtrarConString(null,nombre,primerApellido,segundoApellido);
        }

        log.debug("Resultado del estudiante: {}",estudianteDTO.toString());

        model.addAttribute("estudiante",estudianteDTO);

        return "estudiantes/informe"; // vista con el informe del estudiante
    }

}
