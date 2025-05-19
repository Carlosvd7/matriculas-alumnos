package es.upsa.dasi.web.Adapters.input.dtos;

import jakarta.mvc.binding.MvcBinding;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.FormParam;
import lombok.Data;

@Data
public class AlumnoForm {

    @FormParam("dni")
    @NotNull
    @Size(min = 8, max = 10)
    @MvcBinding
    private String dni;

    @FormParam("nombre")
    @NotNull
    @Size(min = 2, max = 40)
    @MvcBinding
    private String nombre;

    @FormParam("edad")
    @NotNull
    @Min(0)
    @MvcBinding
    private int edad;

    @FormParam("email")
    @NotNull
    @Size(min = 8, max = 10)
    @MvcBinding
    private String email;


}
