package es.upsa.dasi.PracticaExtraordinaria.gateway.Adapters.input.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoDto {
    private String nombre;
    private String email;
    private int edad ;
}
