package es.upsa.dasi.PracticaExtraordinaria.gateway.Application;

import Entities.Alumno;
import Exceptions.AppException;

public interface UpdateAlumnoUseCase {
    Alumno updateAlumno(Alumno alumno) throws AppException;
}
