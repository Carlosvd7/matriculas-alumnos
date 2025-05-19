package es.upsa.dasi.PracticaExtraordinaria.expedientes.Adapters.input.rest.dtos;

import lombok.Data;

@Data
public class ExpedienteDto {
    private String dni;
    private String titulacion;
    private double notaMedia;
    private int credSup;
}
