package es.upsa.dasi.web.Application;

import Entities.Alumno;
import Exceptions.AppException;

public interface AddAlumnoUseCase {
    Alumno addAlumno(Alumno alumno) throws AppException;
}
