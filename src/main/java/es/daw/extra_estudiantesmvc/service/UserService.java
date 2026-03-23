package es.daw.extra_estudiantesmvc.service;

import es.daw.extra_estudiantesmvc.entity.Role;
import es.daw.extra_estudiantesmvc.entity.User;
import es.daw.extra_estudiantesmvc.repository.RoleRepository;
import es.daw.extra_estudiantesmvc.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    // No recibimos el rol porque automáticamente asignamos ROLE_STUDENT

    @Transactional
    public void registerUser(User user) {

        // 1. Codifico la password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 2. Damos por hecho que rol ROLE_STUDENT existe en BD
        Role role = roleRepository.findByName("ROLE_STUDENT")
                .orElseThrow(() -> new RuntimeException("Role not found"));
        // PENDIENTE!!! si no existe el ROLE_STUDENT, darlo de alta en la BD
        // PENDIENTE!!! configurar el role por defecto en un properties...

        // 3. Asigno el role al usuario
        user.addRole(role);

        System.out.println("********** USUARIO A REGISTAR **********");
        System.out.println(user);
        System.out.println("****************************************");

        log.trace("********** USUARIO A REGISTAR **********");
        log.trace(user.toString());
        log.trace("****************************************");

        // 4. Salvar usuario
        userRepository.save(user);

    }

}
