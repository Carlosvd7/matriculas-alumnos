package Entities;

import lombok.Builder;
import lombok.With;

@Builder(setterPrefix = "with")
@With
public record Alumno(
    String dni,
    String nombre,
    int edad,
    String email

){}
