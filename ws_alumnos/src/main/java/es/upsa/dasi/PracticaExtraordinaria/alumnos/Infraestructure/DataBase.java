package es.upsa.dasi.PracticaExtraordinaria.alumnos.Infraestructure;

import Entities.Alumno;
import Exceptions.AppException;
import Exceptions.NonControlledException;
import es.upsa.dasi.PracticaExtraordinaria.alumnos.Adapters.output.Dao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DataBase implements Dao {

    @Inject
    DataSource dataSource;

    @Override
    public List<Alumno> findAllAlumnos() throws AppException {
        final String SQL = """
                           SELECT DNI,NOMBRE,EDAD,EMAIL
                           FROM ALUMNOS
                           """;
        List<Alumno> alumnos = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
        ){
            while (resultSet.next()) {
                alumnos.add(Alumno.builder()
                                .withDni(resultSet.getString(1))
                                .withNombre(resultSet.getString(2))
                                .withEdad(resultSet.getInt(3))
                                .withEmail(resultSet.getString(4))
                                .build()
                );
            }
        }catch (SQLException sqlException){
            throw thrower(sqlException);
        }
        return alumnos;
    }

    @Override
    public Alumno findAlumnoByDni(String dni) throws AppException {
        final String SQL = """
                           SELECT DNI,NOMBRE,EDAD,EMAIL
                           FROM ALUMNOS
                           WHERE DNI = ?
                           """;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
        ){
            statement.setString(1, dni);
            try(ResultSet resultSet = statement.executeQuery();){
                if(resultSet.next()){
                    return Alumno.builder()
                            .withDni(resultSet.getString(1))
                            .withNombre(resultSet.getString(2))
                            .withEdad(resultSet.getInt(3))
                            .withEmail(resultSet.getString(4))
                            .build();
                }else{
                    return null;
                }
            }
        } catch (SQLException sqlException) {
            throw thrower(sqlException);
        }
    }

    public boolean findExpedienteByDni(String dni) throws AppException {
        final String SQL = """
                           SELECT COD,DNI,TITULACION,NOTAMEDIA,CREDSUP
                           FROM EXPEDIENTES
                           WHERE DNI = ?
                           """;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
        ){
            statement.setString(1, dni);
            try(ResultSet resultSet = statement.executeQuery();){
                if(resultSet.next()){
                    return true;
                }else {
                    return false;
                }
            }

        }catch (SQLException sqlException){
            throw thrower(sqlException);
        }
    }

    @Override
    public Alumno addAlumno(Alumno alumno) throws AppException {

        final String SQL = """
                            INSERT INTO ALUMNOS (DNI, NOMBRE, EDAD, EMAIL) VALUES
                            (?,?,?,?)
                           """;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
        ){
            statement.setString(1,alumno.dni());
            statement.setString(2,alumno.nombre());
            statement.setInt(3,alumno.edad());
            statement.setString(4,alumno.email());
            statement.executeUpdate();
            return alumno;
        }catch (SQLException sqlException){
            throw thrower(sqlException);
        }
    }


    @Override
    public Alumno updateAlumno(Alumno alumno) throws AppException {
        final String SQL = """
                           UPDATE ALUMNOS
                           SET DNI=?,NOMBRE=?,EDAD=?,EMAIL=?
                           WHERE DNI=?
                           """;

        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL);
        ){
          statement.setString(1,alumno.dni());
          statement.setString(2,alumno.nombre());
          statement.setInt(3,alumno.edad());
          statement.setString(4,alumno.email());
          statement.setString(5,alumno.dni());
          statement.executeUpdate();
          return alumno;
        }catch (SQLException sqlException){
            throw thrower(sqlException);
        }
    }

    @Override
    public boolean deleleAlumno(String dni) throws AppException {
        final String SQL = """
                           DELETE FROM ALUMNOS
                           WHERE DNI = ?
                           """;

        if(findExpedienteByDni(dni)) return true;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
        ){
            statement.setString(1,dni);
            statement.executeUpdate();
        }catch (SQLException sqlException){
            throw thrower(sqlException);
        }
        return false;
    }


    public AppException thrower(SQLException sqlException) throws AppException{
        return new NonControlledException(sqlException);
    }
}
