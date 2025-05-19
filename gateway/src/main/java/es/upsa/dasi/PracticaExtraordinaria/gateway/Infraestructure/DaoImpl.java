package es.upsa.dasi.PracticaExtraordinaria.gateway.Infraestructure;

import Entities.Alumno;
import Entities.ExceptionDto;
import Entities.Expediente;
import Exceptions.AppException;
import es.upsa.dasi.PracticaExtraordinaria.gateway.Adapters.output.Dao;
import es.upsa.dasi.PracticaExtraordinaria.gateway.Infraestructure.util.Mappers;
import es.upsa.dasi.PracticaExtraordinaria.gateway.Infraestructure.util.UrlUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
public class DaoImpl implements Dao {

    @Inject
    Mappers mappers;

    @Override
    public List<Alumno> findAllAlumnos() throws AppException {
        try(Client client = ClientBuilder.newClient()
        ){
            Response response = client.target(UrlUtils.URL_ALUMNOS)
                    .path("/alumnos")
                    .request(MediaType.APPLICATION_JSON)
                    .get();

            switch(response.getStatusInfo().toEnum()){
                case OK:
                    return response.readEntity(new GenericType<List<Alumno>>() {});
                default:
                    ExceptionDto exception = response.readEntity(ExceptionDto.class);
                    return List.of(Alumno.builder().withNombre(exception.getMessage()).build());
            }
        }
    }

    @Override
    public Alumno findAlumnoByDni(String dni) throws AppException {
        try(Client client = ClientBuilder.newClient()){
            Response response = client.target(UrlUtils.URL_ALUMNOS)
                    .path("/alumnos/{dni}")
                    .resolveTemplate("dni", dni)
                    .request(MediaType.APPLICATION_JSON)
                    .get();

            switch(response.getStatusInfo().toEnum()){
                case OK:
                    return response.readEntity(Alumno.class);
                default:
                    ExceptionDto exception = response.readEntity(ExceptionDto.class);
                    return Alumno.builder().withNombre(exception.getMessage()).build();
            }

        }
    }

    @Override
    public Alumno addAlumno(Alumno alumno) throws AppException {
        try(Client client = ClientBuilder.newClient()){
            Response response = client.target(UrlUtils.URL_ALUMNOS)
                    .path("/alumnos")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(alumno));

            switch(response.getStatusInfo().toEnum()){
                case CREATED:
                    return response.readEntity(Alumno.class);
                default:
                    ExceptionDto exception = response.readEntity(ExceptionDto.class);
                    return Alumno.builder().withNombre(exception.getMessage()).build();
            }


        }
    }

    @Override
    public Alumno updateAlumno(Alumno alumno) throws AppException {
        try(Client client = ClientBuilder.newClient()){
            Response response = client.target(UrlUtils.URL_ALUMNOS)
                    .path("/alumnos/{dni}")
                    .resolveTemplate("dni", alumno.dni())
                    .request(MediaType.APPLICATION_JSON)
                    .put(Entity.json(mappers.toAlumnoDto.apply(alumno)));

            switch(response.getStatusInfo().toEnum()){
                case OK:
                    return response.readEntity(Alumno.class);
                default:
                    ExceptionDto exception = response.readEntity(ExceptionDto.class);
                    return Alumno.builder().withNombre(exception.getMessage()).build();
            }

        }
    }

    @Override
    public String deleteAlumno(String dni) throws AppException {
        try(Client client = ClientBuilder.newClient()){
            Response response = client.target(UrlUtils.URL_ALUMNOS)
                    .path("/alumnos/{dni}")
                    .resolveTemplate("dni", dni)
                    .request(MediaType.APPLICATION_JSON)
                    .delete();

            switch(response.getStatusInfo().toEnum()){
                case NO_CONTENT:
                    return null;
                default:
                    ExceptionDto exception = response.readEntity(ExceptionDto.class);
                    return exception.getMessage();

            }

        }
    }


    @Override
    public Expediente findExpedienteByCod(String cod) throws AppException {
        try(Client client = ClientBuilder.newClient()){
            Response response = client.target(UrlUtils.URL_EXPEDIENTES)
                    .path("/expedientes/{cod}")
                    .resolveTemplate("cod", cod)
                    .request(MediaType.APPLICATION_JSON)
                    .get();

            switch(response.getStatusInfo().toEnum()){
                case OK:
                    return response.readEntity(Expediente.class);
                default:
                    ExceptionDto exception = response.readEntity(ExceptionDto.class);
                    return Expediente.builder().withDni(exception.getMessage()).build();
            }
        }
    }

    @Override
    public Expediente findExpedienteByDni(String dni) throws AppException {
        try(Client client = ClientBuilder.newClient()){
            Response response = client.target(UrlUtils.URL_EXPEDIENTES)
                    .path("/expedientes/dni/{dni}")
                    .resolveTemplate("dni", dni)
                    .request(MediaType.APPLICATION_JSON)
                    .get();

            switch(response.getStatusInfo().toEnum()){
                case OK:
                    return response.readEntity(Expediente.class);
                default:
                    ExceptionDto exception = response.readEntity(ExceptionDto.class);
                    return Expediente.builder().withDni(exception.getMessage()).build();
            }
        }
    }

    @Override
    public Expediente addExpediente(Expediente expediente) throws AppException {
        try(Client client = ClientBuilder.newClient()){
            Response response = client.target(UrlUtils.URL_EXPEDIENTES)
                    .path("/expedientes")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(mappers.toExpedienteDto.apply(expediente)));


            switch(response.getStatusInfo().toEnum()){
                case CREATED:
                    return response.readEntity(Expediente.class);
                default:
                    ExceptionDto exception = response.readEntity(ExceptionDto.class);
                    return Expediente.builder().withDni(exception.getMessage()).build();
            }
        }
    }

    @Override
    public Expediente updateExpediente(Expediente expediente) throws AppException {
        try(Client client = ClientBuilder.newClient()){
            Response response = client.target(UrlUtils.URL_EXPEDIENTES)
                    .path("/expedientes/{cod}")
                    .resolveTemplate("cod", expediente.cod())
                    .request(MediaType.APPLICATION_JSON)
                    .put(Entity.json(mappers.toExpedienteDto.apply(expediente)));

            switch (response.getStatusInfo().toEnum()){
                case OK:
                    return response.readEntity(Expediente.class);
                default:
                    ExceptionDto exception = response.readEntity(ExceptionDto.class);
                    return Expediente.builder().withDni(exception.getMessage()).build();
            }
        }
    }

    @Override
    public String deleteExpediente(String cod) throws AppException {
        try(Client client = ClientBuilder.newClient()){
            Response response = client.target(UrlUtils.URL_EXPEDIENTES)
                    .path("/expedientes/{cod}")
                    .resolveTemplate("cod", cod)
                    .request(MediaType.APPLICATION_JSON)
                    .delete();

            switch(response.getStatusInfo().toEnum()){
                case NO_CONTENT:
                    return null;
                default:
                    ExceptionDto exception = response.readEntity(ExceptionDto.class);
                    return exception.getMessage();
            }

        }
    }
}
