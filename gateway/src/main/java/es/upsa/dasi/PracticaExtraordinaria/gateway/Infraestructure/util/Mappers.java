package es.upsa.dasi.PracticaExtraordinaria.gateway.Infraestructure.util;

import Entities.Alumno;
import Entities.Expediente;
import es.upsa.dasi.PracticaExtraordinaria.gateway.Adapters.input.dtos.AlumnoDto;
import es.upsa.dasi.PracticaExtraordinaria.gateway.Adapters.input.dtos.ExpedienteDto;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.function.Function;

@ApplicationScoped
public class Mappers {

    public Function<Alumno, AlumnoDto> toAlumnoDto = alumno -> AlumnoDto.builder()
            .withNombre(alumno.nombre())
            .withEdad(alumno.edad())
            .withEmail(alumno.email())
            .build();

    public Function<Expediente, ExpedienteDto> toExpedienteDto = expediente -> ExpedienteDto.builder()
            .withDni(expediente.dni())
            .withCredSup(expediente.credSup())
            .withTitulacion(expediente.titulacion())
            .withNotaMedia(expediente.notaMedia())
            .build();



}
