package es.upsa.dasi.PracticaExtraordinaria.gateway.Application.impl;

import Exceptions.AppException;
import es.upsa.dasi.PracticaExtraordinaria.gateway.Application.DeleteAlumnoUseCase;
import es.upsa.dasi.PracticaExtraordinaria.gateway.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteAlumnoUseCaseImpl implements DeleteAlumnoUseCase {

    @Inject
    Repository repository;

    @Override
    public String deleteAlumno(String dni) throws AppException {
        return repository.deleteAlumno(dni);
    }
}
