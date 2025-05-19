package es.upsa.dasi.PracticaExtraordinaria.alumnos.Adapters.input;

import Entities.Alumno;
import Entities.ExceptionDto;
import Exceptions.AppException;
import es.upsa.dasi.PracticaExtraordinaria.alumnos.Adapters.input.dtos.AlumnoDto;
import es.upsa.dasi.PracticaExtraordinaria.alumnos.Application.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;

@Path("/alumnos")
public class AlumnosResource {


    @Context
    UriInfo uriInfo;
    @Inject
    FindAllAlumnosUseCase findAllAlumnosUseCase;
    @Inject
    FindAlumnoByDniUseCase findAlumnoByDniUseCase;
    @Inject
    AddAlumnoUseCase addAlumnoUseCase;
    @Inject
    UpdateAlumnoUseCase updateAlumnoUseCase;
    @Inject
    DeleteAlumnoUseCase deleteAlumnoUseCase;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllAlumnos() throws AppException {
        List<Alumno> alumnos = findAllAlumnosUseCase.execute();
        return Response.ok()
                .entity(alumnos)
                .build();
    }

    @GET
    @Path("/{dni}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAlumnoByDni(@PathParam("dni") String dni) throws AppException {
        Alumno alumno = findAlumnoByDniUseCase.execute(dni);
        if(alumno == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ExceptionDto.builder()
                            .withMessage("No existe el alumno con DNI " + dni)
                            .build())
                    .build();
        }
        return Response.ok()
                .entity(alumno)
                .build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAlumno(Alumno alumno) throws AppException {

        if(findAlumnoByDniUseCase.execute(alumno.dni())!=null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ExceptionDto.builder()
                            .withMessage("El alumno con el dni: " + alumno.dni() + " ya existe")
                            .build())
                    .build();
        }

        Alumno newAlumno = addAlumnoUseCase.execute(alumno);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .path("/{dni}")
                .resolveTemplate("dni", newAlumno.dni())
                .build();

        return Response.created(uri)
                .entity(newAlumno)
                .build();
    }

    @PUT
    @Path("/{dni}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAlumno(@PathParam("dni")String dni, AlumnoDto alumnoDto) throws AppException {
        Alumno alumno = findAlumnoByDniUseCase.execute(dni);
        if(alumno == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ExceptionDto.builder()
                            .withMessage("El alumno con el dni: " + dni + " no existe")
                            .build())
                    .build();
        }

        Alumno alumno1 = Alumno.builder()
                .withDni(dni)
                .withNombre(alumnoDto.getNombre())
                .withEdad(alumnoDto.getEdad())
                .withEmail(alumnoDto.getEmail())
                .build();

        Alumno alumno2 = updateAlumnoUseCase.execute(alumno1);

        return Response.ok()
                .entity(alumno2)
                .build();


    }

    @DELETE
    @Path("{dni}")
    public Response deleteAlumno(@PathParam("dni")String dni) throws AppException {
        Alumno alumno = findAlumnoByDniUseCase.execute(dni);
        if(alumno == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ExceptionDto.builder()
                            .withMessage("El alumno con el dni: " + dni + " no existe")
                            .build())
                    .build();
        }

        boolean borrado = deleteAlumnoUseCase.deleteAlumno(dni);
        if(borrado){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ExceptionDto.builder()
                            .withMessage("El alumno con el dni: " + dni + " tiene un expediente asignado")
                            .build())
                    .build();
        }
        return Response.noContent()
                .build();
    }


}
