package es.upsa.dasi.PracticaExtraordinaria.gateway.Application.impl;

import Entities.Alumno;
import Exceptions.AppException;
import es.upsa.dasi.PracticaExtraordinaria.gateway.Application.UpdateAlumnoUseCase;
import es.upsa.dasi.PracticaExtraordinaria.gateway.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.resteasy.plugins.providers.ReaderProvider;

@ApplicationScoped

public class UpdateAlumnoUseCaseImpl implements UpdateAlumnoUseCase {

    @Inject
    Repository repository;

    @Override
    public Alumno updateAlumno(Alumno alumno) throws AppException {
        return repository.updateAlumno(alumno);
    }
}
