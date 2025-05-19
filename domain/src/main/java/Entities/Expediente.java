package Entities;

import lombok.Builder;
import lombok.With;

@Builder(setterPrefix = "with")
@With
public record Expediente(
    String cod,
    String dni,
    double notaMedia,
    String titulacion,
    int credSup
){}
