package es.upsa.dasi.web.Application;

import Exceptions.AppException;

public interface DeleteAlumnoUseCase {
    String deleteAlumno(String dni) throws AppException;
}
