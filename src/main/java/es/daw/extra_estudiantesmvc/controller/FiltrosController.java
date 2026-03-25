package es.daw.extra_estudiantesmvc.controller;

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

        if (nia != null){
            filtrosService.filtrar("");
        }else if (nombre != null && primerApellido != null && segundoApellido != null){
            filtrosService.filtrar("");
        }

        return "estudiantes/informe";
    }

}
