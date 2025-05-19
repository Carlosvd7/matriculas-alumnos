package es.upsa.dasi.PracticaExtraordinaria.alumnos.Application;

import Entities.Alumno;
import Exceptions.AppException;

public interface FindAlumnoByDniUseCase {
    Alumno execute(String dni) throws AppException;
}
