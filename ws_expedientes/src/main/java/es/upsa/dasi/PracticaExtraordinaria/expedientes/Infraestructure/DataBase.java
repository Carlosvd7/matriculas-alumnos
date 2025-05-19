package es.upsa.dasi.PracticaExtraordinaria.expedientes.Infraestructure;


import Entities.Alumno;
import Entities.Expediente;
import Exceptions.AppException;
import Exceptions.NonControlledException;
import es.upsa.dasi.PracticaExtraordinaria.expedientes.Adapters.output.Dao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped
public class DataBase implements Dao {

    @Inject
    DataSource dataSource;

    @Override
    public Expediente findExpedienteByCod(String cod) throws AppException {
        final String SQL = """
                           SELECT COD,DNI,TITULACION,NOTAMEDIA,CREDSUP
                           FROM EXPEDIENTES
                           WHERE COD = ? 
                           """;

        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL);
        ){
            ps.setString(1, cod);
            try(ResultSet rs = ps.executeQuery();){
                if(rs.next()){
                    return Expediente.builder()
                            .withCod(rs.getString(1))
                            .withDni(rs.getString(2))
                            .withTitulacion(rs.getString(3))
                            .withNotaMedia(rs.getDouble(4))
                            .withCredSup(rs.getInt(5))
                            .build();
                }else {
                    return null;
                }
            }
        }catch (SQLException sqlException){
            throw thrower(sqlException);
        }
    }


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


    @Override
    public Expediente findExpedienteByDni(String dni) throws AppException {
        final String SQL = """
                           SELECT COD,DNI,TITULACION,NOTAMEDIA,CREDSUP
                           FROM EXPEDIENTES
                           WHERE DNI = ? 
                           """;

        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL);
        ){
            ps.setString(1, dni);
            try(ResultSet rs = ps.executeQuery();){
                if(rs.next()){
                    return Expediente.builder()
                            .withCod(rs.getString(1))
                            .withDni(rs.getString(2))
                            .withTitulacion(rs.getString(3))
                            .withNotaMedia(rs.getDouble(4))
                            .withCredSup(rs.getInt(5))
                            .build();
                }else {
                    return null;
                }
            }
        }catch (SQLException sqlException){
            throw thrower(sqlException);
        }
    }


    @Override
    public Expediente addExpediente(Expediente expediente) throws AppException {
        final String SQL = """
                            INSERT INTO EXPEDIENTES (COD, DNI, TITULACION, NOTAMEDIA, CREDSUP) VALUES
                            (nextval('seq_expedientes'), ?, ?, ?, ?)
                           """;

        Alumno alumnoByDni = findAlumnoByDni(expediente.dni());
        if(alumnoByDni == null){
            return null;
        }
        String [] columns = {"cod"};
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL,columns);
        ){
            ps.setString(1, expediente.dni());
            ps.setString(2, expediente.titulacion());
            ps.setDouble(3, expediente.notaMedia());
            ps.setInt(4, expediente.credSup());
            ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()){
                rs.next();
                String cod = rs.getString(1);
                return expediente.withCod(cod);
            }

        }catch (SQLException sqlException){
            throw thrower(sqlException);
        }
    }

    @Override
    public Expediente updateExpediente(Expediente expediente) throws AppException {
        final String SQL = """
                           UPDATE EXPEDIENTES
                               SET DNI = ?,
                               TITULACION = ?,
                               NOTAMEDIA = ?,
                               CREDSUP = ?                             
                           WHERE COD = ?
                           """;
        Alumno alumnoByDni = findAlumnoByDni(expediente.dni());
        if(alumnoByDni == null){
            return null;
        }
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL);
        ){
            ps.setString(1, expediente.dni());
            ps.setString(2, expediente.titulacion());
            ps.setDouble(3, expediente.notaMedia());
            ps.setInt(4, expediente.credSup());
            ps.setString(5, expediente.cod());
            ps.executeUpdate();
            return expediente;
        }catch (SQLException sqlException){
            throw thrower(sqlException);
        }
    }

    @Override
    public String deleteExpediente(String cod) throws AppException {
        final String SQL = """
                           DELETE FROM EXPEDIENTES
                           WHERE COD = ?
                           """;

        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL);
        ){
            ps.setString(1, cod);
            ps.executeUpdate();
            return null;
        }catch (SQLException sqlException){
            throw thrower(sqlException);
        }
    }

    public AppException thrower(SQLException sqlException) throws AppException{
        return new NonControlledException(sqlException);
    }
}
