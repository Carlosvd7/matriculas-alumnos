package es.upsa.dasi.web.Adapters.input.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class ExpedienteDto {
    private String dni;
    private double notaMedia;
    private String titulacion;
    private int credSup;
}
