package es.upsa.dasi.PracticaExtraordinaria.alumnos.Application.impl;

import Exceptions.AppException;
import es.upsa.dasi.PracticaExtraordinaria.alumnos.Application.DeleteAlumnoUseCase;
import es.upsa.dasi.PracticaExtraordinaria.alumnos.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteAlumnoUseCaseImpl implements DeleteAlumnoUseCase {

    @Inject
    Repository repository;

    @Override
    public boolean deleteAlumno(String dni) throws AppException {
        return repository.deleteAlumno(dni);
    }
}
