package es.upsa.dasi.PracticaExtraordinaria.alumnos.Domain;

import Entities.Alumno;
import Exceptions.AppException;

import java.util.List;

public interface Repository {
    List<Alumno> findAllAlumnos() throws AppException;


    Alumno findAlumnoByDni(String dni) throws AppException;

    Alumno addAlumno(Alumno alumno) throws AppException;

    Alumno updateAlumno(Alumno alumno) throws AppException;

    boolean deleteAlumno(String dni) throws AppException;
}
