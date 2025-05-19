package es.upsa.dasi.PracticaExtraordinaria.alumnos.Application.impl;

import Entities.Alumno;
import Exceptions.AppException;
import es.upsa.dasi.PracticaExtraordinaria.alumnos.Application.FindAllAlumnosUseCase;
import es.upsa.dasi.PracticaExtraordinaria.alumnos.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class FindAllAlumnosUseCaseImpl implements FindAllAlumnosUseCase {

    @Inject
    Repository repository;


    @Override
    public List<Alumno> execute() throws AppException {
        return repository.findAllAlumnos();
    }
}
