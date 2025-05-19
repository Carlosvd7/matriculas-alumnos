package es.upsa.dasi.web.Application;

import Entities.Alumno;
import Exceptions.AppException;

public interface FindAlumnoByDniUseCase {
    Alumno findAlumnoByDni(String dni) throws AppException;
}
