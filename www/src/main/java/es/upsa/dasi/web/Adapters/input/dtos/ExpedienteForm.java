package es.upsa.dasi.web.Adapters.input.dtos;

import jakarta.mvc.binding.MvcBinding;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.FormParam;
import lombok.Data;

@Data
public class ExpedienteForm {

    @FormParam("dni")
    @NotNull
    @Size(min = 8, max = 11)
    @MvcBinding
    private String dni;

    @FormParam("notaMedia")
    @Min(0)
    @Max(10)
    @MvcBinding
    private double notaMedia;

    @FormParam("titulacion")
    @NotNull
    @Size(min = 2, max = 40)
    @MvcBinding
    private String titulacion;

    @FormParam("credSup")
    @Min(0)
    @Max(240)
    @MvcBinding
    private int credSup;




}
