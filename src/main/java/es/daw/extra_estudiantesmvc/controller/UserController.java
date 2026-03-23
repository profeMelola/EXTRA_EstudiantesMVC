package es.daw.extra_estudiantesmvc.controller;

import es.daw.extra_estudiantesmvc.entity.User;
import es.daw.extra_estudiantesmvc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String mostrarRegistro(Model model) {

        // No es mejor trabajar con un DTO de usuario, con solo los campos
        // a dar de alta del usuario??? y luego convertirlo con un mapper a Entity para
        // salvar en BD???
        model.addAttribute("user", new User());

        return "estudiantes/registro";
    }


    @PostMapping("/register")
    public String registrar(@ModelAttribute("user") User user) {

        // Utilizar un servicio para acceso a BD (repo)
        userService.registerUser(user);

        return "redirect:/login";

    }

//    @GetMapping("/login")
//    public String login(){}

}
