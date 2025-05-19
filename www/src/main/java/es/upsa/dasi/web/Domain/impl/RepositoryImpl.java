package es.upsa.dasi.web.Domain.impl;

import Entities.Alumno;
import Entities.Expediente;
import Exceptions.AppException;
import es.upsa.dasi.web.Adapters.output.Dao;
import es.upsa.dasi.web.Domain.Repository;
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
    public String deleteAlumno(String dni) throws AppException {
        return dao.deleteAlumno(dni);
    }

    @Override
    public Expediente findExpedienteByCod(String cod) throws AppException {
        return dao.findExpedienteByCod(cod);
    }

    @Override
    public Expediente findExpedienteByDni(String dni) throws AppException {
        return dao.findExpedienteByDni(dni);
    }

    @Override
    public Expediente addRepository(Expediente expediente) throws AppException {
        return dao.addExpediente(expediente);
    }

    @Override
    public Expediente updateExpediente(Expediente expediente) throws AppException {
        return dao.updateExpediente(expediente);
    }

    @Override
    public String deleteExpediente(String cod) throws AppException {
        return dao.deleteExpediente(cod);
    }
}
