package es.daw.extra_estudiantesmvc.repository;

import es.daw.extra_estudiantesmvc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
