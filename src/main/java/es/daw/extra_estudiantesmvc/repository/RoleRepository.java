package es.daw.extra_estudiantesmvc.repository;

import es.daw.extra_estudiantesmvc.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
