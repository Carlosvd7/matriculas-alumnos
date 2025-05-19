package es.upsa.dasi.PracticaExtraordinaria.gateway.Application;

import Entities.Alumno;
import Exceptions.AppException;

import java.util.List;

public interface FindAllAlumnoUseCase {
    List<Alumno> execute() throws AppException;
}
