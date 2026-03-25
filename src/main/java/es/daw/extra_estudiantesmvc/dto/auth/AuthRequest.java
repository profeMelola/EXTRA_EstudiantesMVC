package es.daw.extra_estudiantesmvc.dto.auth;

public record AuthRequest(
        String username,
        String password
) {
}
