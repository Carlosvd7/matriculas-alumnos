package es.upsa.dasi.PracticaExtraordinaria.gateway.Domain;

import Entities.Alumno;
import Entities.Expediente;
import Exceptions.AppException;

import java.util.List;

public interface Repository {
    List<Alumno> findAllAlumnos() throws AppException;

    Alumno findAlumnoByDni(String dni) throws AppException;

    Alumno addAlumno(Alumno alumno) throws AppException;

    Alumno updateAlumno(Alumno alumno) throws AppException;

    String deleteAlumno(String dni) throws AppException;

    Expediente findExpedienteByCod(String cod) throws AppException;

    Expediente findExpedienteByDni(String dni) throws AppException;

    Expediente addExpediente(Expediente expediente) throws AppException;

    Expediente updateExpediente(Expediente expediente) throws AppException;

    String deleteExpediente(String cod) throws AppException;
}
