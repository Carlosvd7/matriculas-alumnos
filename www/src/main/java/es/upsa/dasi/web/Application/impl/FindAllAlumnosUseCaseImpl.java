package es.upsa.dasi.web.Application.impl;

import Entities.Alumno;
import Exceptions.AppException;
import es.upsa.dasi.web.Application.FindAllAlumnosUseCase;
import es.upsa.dasi.web.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class FindAllAlumnosUseCaseImpl implements FindAllAlumnosUseCase {

    @Inject
    Repository repository;

    @Override
    public List<Alumno> findAll() throws AppException {
        return repository.findAllAlumnos();
    }
}
