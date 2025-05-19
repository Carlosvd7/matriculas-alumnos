package es.upsa.dasi.PracticaExtraordinaria.alumnos.Application;

import Entities.Alumno;
import Exceptions.AppException;

public interface UpdateAlumnoUseCase {
    Alumno execute(Alumno alumno) throws AppException;
}
