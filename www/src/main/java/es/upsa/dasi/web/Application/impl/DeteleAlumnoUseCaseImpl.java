package es.upsa.dasi.web.Application.impl;

import Exceptions.AppException;
import es.upsa.dasi.web.Application.DeleteAlumnoUseCase;
import es.upsa.dasi.web.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeteleAlumnoUseCaseImpl implements DeleteAlumnoUseCase {

    @Inject
    Repository repository;

    @Override
    public String deleteAlumno(String dni) throws AppException {
        return repository.deleteAlumno(dni);
    }
}
