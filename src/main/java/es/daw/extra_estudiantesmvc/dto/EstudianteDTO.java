package es.daw.extra_estudiantesmvc.dto;
/*
  "direccion": "Avenida Gran Vía 23, Madrid",
  "edad": 26,
  "email": "juan.perez2@example.com",
  "fechaNacimiento": "1999-08-22",
  "movil": "601123456",
  "nia": "NIA_1002",
  "nombre": "Juan",
  "primerApellido": "Pérez",
  "segundoApellido": "López"
 */
public record EstudianteDTO(
       String nia,
       String nombre,
       String primerApellido,
       String segundoApellido,
       String email,
       String movil,
       String direccion,
       String fechaNacimiento,
       int edad
) {
}
