package es.upsa.dasi.web.Application.impl;

import Entities.Alumno;
import Exceptions.AppException;
import es.upsa.dasi.web.Application.AddAlumnoUseCase;
import es.upsa.dasi.web.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AddAlumnoUseCaseImpl implements AddAlumnoUseCase {

    @Inject
    Repository repository;

    @Override
    public Alumno addAlumno(Alumno alumno) throws AppException {
        return repository.addAlumno(alumno);
    }
}
