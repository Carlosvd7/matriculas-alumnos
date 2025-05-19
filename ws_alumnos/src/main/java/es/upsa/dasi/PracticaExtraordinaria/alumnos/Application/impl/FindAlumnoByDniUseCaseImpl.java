package es.upsa.dasi.PracticaExtraordinaria.alumnos.Application.impl;

import Entities.Alumno;
import Exceptions.AppException;
import es.upsa.dasi.PracticaExtraordinaria.alumnos.Application.FindAlumnoByDniUseCase;
import es.upsa.dasi.PracticaExtraordinaria.alumnos.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FindAlumnoByDniUseCaseImpl implements FindAlumnoByDniUseCase {

    @Inject
    Repository repository;

    @Override
    public Alumno execute(String dni) throws AppException {
        return repository.findAlumnoByDni(dni);
    }
}
