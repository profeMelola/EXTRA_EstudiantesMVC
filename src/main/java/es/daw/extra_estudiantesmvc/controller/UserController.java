package es.daw.extra_estudiantesmvc.controller;

import es.daw.extra_estudiantesmvc.entity.User;
import es.daw.extra_estudiantesmvc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/login")
    public String mostrarLogin(@RequestParam(value="error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Usuario o pwd incorrectos!!!!");
        }
        return "login";

    }

    // ------------------

    @GetMapping("/register")
    public String mostrarRegistro(Model model) {

        // No es mejor trabajar con un DTO de usuario, con solo los campos
        // a dar de alta del usuario??? y luego convertirlo con un mapper a Entity para
        // salvar en BD???
        model.addAttribute("user", new User());

        return "estudiantes/registro";
    }


    @PostMapping("/register")
    public String registrar(@ModelAttribute("user") User user,
                            RedirectAttributes redirectAttributes) {

        // Utilizar un servicio para acceso a BD (repo)
        userService.registerUser(user);

        // PENDIENTE!!! MEJORA!!! Mandar mensaje a login de que todo ha ido ok
        redirectAttributes.addFlashAttribute("success", "Usuario registrado correctamente. Por favor, inicie sesión!!!");

        return "redirect:/login";

    }

//    @GetMapping("/login")
//    public String login(){}

}
