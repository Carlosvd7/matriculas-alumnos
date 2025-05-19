package es.upsa.dasi.PracticaExtraordinaria.alumnos.Domain.impl;

import Entities.Alumno;
import Exceptions.AppException;
import es.upsa.dasi.PracticaExtraordinaria.alumnos.Adapters.output.Dao;
import es.upsa.dasi.PracticaExtraordinaria.alumnos.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;


@ApplicationScoped
public class RepositoryImpl implements Repository {

    @Inject
    Dao dao;

    @Override
    public List<Alumno> findAllAlumnos() throws AppException {
        return dao.findAllAlumnos();
    }

    @Override
    public Alumno findAlumnoByDni(String dni) throws AppException {
        return dao.findAlumnoByDni(dni);
    }

    @Override
    public Alumno addAlumno(Alumno alumno) throws AppException {
        return dao.addAlumno(alumno);
    }

    @Override
    public Alumno updateAlumno(Alumno alumno) throws AppException {
        return dao.updateAlumno(alumno);
    }

    @Override
    public boolean deleteAlumno(String dni) throws AppException {
        return dao.deleleAlumno(dni);

    }
}
