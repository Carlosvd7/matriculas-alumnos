package es.upsa.dasi.web.Application;

import Entities.Alumno;
import Exceptions.AppException;

public interface UpdateAlumnoUseCase {
    Alumno updateAlumno(Alumno alumno)throws AppException;
}
