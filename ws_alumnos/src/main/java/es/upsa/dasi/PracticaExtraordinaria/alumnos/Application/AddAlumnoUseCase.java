package es.upsa.dasi.PracticaExtraordinaria.alumnos.Application;

import Entities.Alumno;
import Exceptions.AppException;

public interface AddAlumnoUseCase {
    Alumno execute(Alumno alumno) throws AppException;
}
