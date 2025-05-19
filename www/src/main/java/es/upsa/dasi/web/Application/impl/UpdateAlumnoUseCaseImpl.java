package es.upsa.dasi.web.Application.impl;

import Entities.Alumno;
import Exceptions.AppException;
import es.upsa.dasi.web.Application.UpdateAlumnoUseCase;
import es.upsa.dasi.web.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UpdateAlumnoUseCaseImpl implements UpdateAlumnoUseCase {

    @Inject
    Repository repository;

    @Override
    public Alumno updateAlumno(Alumno alumno) throws AppException {
        return repository.updateAlumno(alumno);
    }
}
