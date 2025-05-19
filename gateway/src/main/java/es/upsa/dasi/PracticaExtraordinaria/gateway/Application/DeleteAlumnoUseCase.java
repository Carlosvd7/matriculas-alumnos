package es.upsa.dasi.PracticaExtraordinaria.gateway.Application;

import Entities.Alumno;
import Exceptions.AppException;

public interface DeleteAlumnoUseCase {
    String deleteAlumno(String dni) throws AppException;
}
