package es.upsa.dasi.PracticaExtraordinaria.gateway.Application.impl;

import Entities.Alumno;
import Exceptions.AppException;
import es.upsa.dasi.PracticaExtraordinaria.gateway.Application.FindAllAlumnoUseCase;
import es.upsa.dasi.PracticaExtraordinaria.gateway.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class FindAllAlumnoUseCaseImpl implements FindAllAlumnoUseCase {

    @Inject
    Repository repository;


    @Override
    public List<Alumno> execute() throws AppException {
        return repository.findAllAlumnos();
    }
}
