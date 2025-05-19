package es.upsa.dasi.PracticaExtraordinaria.alumnos.Application;

import Exceptions.AppException;

public interface DeleteAlumnoUseCase {
    boolean deleteAlumno(String dni) throws AppException;
}
