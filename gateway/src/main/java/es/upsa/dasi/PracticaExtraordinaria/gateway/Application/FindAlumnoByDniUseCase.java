package es.upsa.dasi.PracticaExtraordinaria.gateway.Application;

import Entities.Alumno;
import Exceptions.AppException;

public interface FindAlumnoByDniUseCase {
    Alumno findAlumnoByDni(String dni) throws AppException;
}
