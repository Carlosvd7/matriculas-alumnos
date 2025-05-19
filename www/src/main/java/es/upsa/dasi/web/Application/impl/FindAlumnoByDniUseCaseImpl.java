package es.upsa.dasi.web.Application.impl;

import Entities.Alumno;
import Exceptions.AppException;
import es.upsa.dasi.web.Application.FindAlumnoByDniUseCase;
import es.upsa.dasi.web.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FindAlumnoByDniUseCaseImpl implements FindAlumnoByDniUseCase {

    @Inject
    Repository repository;

    @Override
    public Alumno findAlumnoByDni(String dni) throws AppException {
        return repository.findAlumnoByDni(dni);
    }
}
