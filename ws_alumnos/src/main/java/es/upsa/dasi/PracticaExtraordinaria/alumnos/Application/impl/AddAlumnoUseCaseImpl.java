package es.upsa.dasi.PracticaExtraordinaria.alumnos.Application.impl;

import Entities.Alumno;
import Exceptions.AppException;
import es.upsa.dasi.PracticaExtraordinaria.alumnos.Application.AddAlumnoUseCase;
import es.upsa.dasi.PracticaExtraordinaria.alumnos.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AddAlumnoUseCaseImpl implements AddAlumnoUseCase {

    @Inject
    Repository repository;

    @Override
    public Alumno execute(Alumno alumno) throws AppException {
        return repository.addAlumno(alumno);
    }
}
