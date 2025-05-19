package es.upsa.dasi.PracticaExtraordinaria.alumnos.Adapters.output;

import Entities.Alumno;
import Exceptions.AppException;

import java.util.List;

public interface Dao {
    List<Alumno> findAllAlumnos() throws AppException;

    Alumno findAlumnoByDni(String dni) throws AppException;

    Alumno addAlumno(Alumno alumno) throws AppException;

    Alumno updateAlumno(Alumno alumno) throws AppException;

    boolean deleleAlumno(String dni) throws AppException;
}
