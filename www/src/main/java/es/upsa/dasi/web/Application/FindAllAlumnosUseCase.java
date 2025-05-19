package es.upsa.dasi.web.Application;

import Entities.Alumno;
import Exceptions.AppException;

import java.util.List;

public interface FindAllAlumnosUseCase {
    List<Alumno> findAll() throws AppException;
}
