package es.upsa.dasi.web.Infraestructure.util;

import Entities.Alumno;
import Entities.Expediente;
import es.upsa.dasi.web.Adapters.input.dtos.AlumnoDto;
import es.upsa.dasi.web.Adapters.input.dtos.ExceptionDto;
import es.upsa.dasi.web.Adapters.input.dtos.ExpedienteDto;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Mappers {

    public AlumnoDto toAlumnoDto(Alumno alumno) {
        return AlumnoDto.builder()
                .withNombre(alumno.nombre())
                .withEdad(alumno.edad())
                .withEmail(alumno.email())
                .build();

    }

    public ExpedienteDto toExpedienteDto(Expediente expediente) {
        return ExpedienteDto.builder()
                .withDni(expediente.dni())
                .withTitulacion(expediente.titulacion())
                .withNotaMedia(expediente.notaMedia())
                .withCredSup(expediente.credSup())
                .build();
    }

}
