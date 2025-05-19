package es.upsa.dasi.PracticaExtraordinaria.alumnos.Application;

import Entities.Alumno;
import Exceptions.AppException;

import java.util.List;

public interface FindAllAlumnosUseCase {
    List<Alumno> execute() throws AppException;
}
