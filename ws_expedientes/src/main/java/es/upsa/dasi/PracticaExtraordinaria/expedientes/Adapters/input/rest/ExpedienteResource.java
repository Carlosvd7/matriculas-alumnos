package es.upsa.dasi.PracticaExtraordinaria.expedientes.Adapters.input.rest;

import Entities.ExceptionDto;
import Entities.Expediente;
import Exceptions.AppException;
import es.upsa.dasi.PracticaExtraordinaria.expedientes.Adapters.input.rest.dtos.ExpedienteDto;
import es.upsa.dasi.PracticaExtraordinaria.expedientes.Application.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;

@Path("/expedientes")
@ApplicationScoped
public class ExpedienteResource {

    @Context
    UriInfo uriInfo;
    @Inject
    FindExpedienteByCodUseCase findExpedienteByCodUseCase;
    @Inject
    FindExpedienteByDniUseCase findExpedienteByDniUseCase;
    @Inject
    AddExpedienteUseCase addExpedienteUseCase;
    @Inject
    UpdateExpedienteUseCase updateExpedienteUseCase;
    @Inject
    DeleteExpedienteUseCase deleteExpedienteUseCase;

    @GET
    @Path("/{cod}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findExpdienteByCod(@PathParam("cod") String cod) throws AppException {
        Expediente expediente = findExpedienteByCodUseCase.execute(cod);
        if(expediente == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ExceptionDto.builder()
                            .withMessage("No existe el expediente con el codigo: " + cod)
                            .build())
                    .build();
        }
        return Response.ok()
                .entity(expediente)
                .build();
    }

    @GET
    @Path("/dni/{dni}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findExpdienteByDni(@PathParam("dni") String dni) throws AppException {
        Expediente expediente = findExpedienteByDniUseCase.execute(dni);
        if(expediente == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ExceptionDto.builder()
                            .withMessage("No existe el expediente con el dni: " + dni)
                            .build())
                    .build();
        }
        return Response.ok()
                .entity(expediente)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addExpediente(ExpedienteDto expedienteDto) throws AppException {
        Expediente expediente = Expediente.builder()
                .withCod(null)
                .withDni(expedienteDto.getDni())
                .withNotaMedia(expedienteDto.getNotaMedia())
                .withCredSup(expedienteDto.getCredSup())
                .withTitulacion(expedienteDto.getTitulacion())
                .build();

        Expediente expedienteDni = findExpedienteByDniUseCase.execute(expedienteDto.getDni());
        if(expedienteDni != null){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ExceptionDto.builder()
                            .withMessage("El alumno ya tiene un expediente")
                            .build())
                    .build();
        }

        Expediente expediente1 = addExpedienteUseCase.execute(expediente);

        if(expediente1==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ExceptionDto.builder()
                            .withMessage("El alumno con el dni: " + expedienteDto.getDni() + " no existe")
                            .build())
                    .build();
        }
        URI uri = uriInfo.getAbsolutePathBuilder()
                .path("/{cod}")
                .resolveTemplate("cod", expediente1.cod())
                .build();

        return Response.created(uri)
                .entity(expediente1)
                .build();

    }

    @PUT
    @Path("/{cod}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateExpediente(@PathParam("cod") String cod, ExpedienteDto expedienteDto) throws AppException {
        Expediente expediente = Expediente.builder()
                .withCod(cod)
                .withDni(expedienteDto.getDni())
                .withNotaMedia(expedienteDto.getNotaMedia())
                .withCredSup(expedienteDto.getCredSup())
                .withTitulacion(expedienteDto.getTitulacion())
                .build();

        Expediente expediente1 = findExpedienteByCodUseCase.execute(cod);
        if(expediente1 == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ExceptionDto.builder()
                            .withMessage("No existe el expediente con el codigo: " + cod)
                            .build())
                    .build();
        }

        Expediente expedienteDni = findExpedienteByDniUseCase.execute(expediente.dni());
        if(expedienteDni != null && !expediente1.dni().equals(expedienteDni.dni())){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ExceptionDto.builder()
                            .withMessage("El alumno ya tiene un expediente")
                            .build())
                    .build();
        }


        Expediente updateExpediente = updateExpedienteUseCase.execute(expediente);
        if(updateExpediente==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ExceptionDto.builder()
                            .withMessage("El alumno con el dni: " + expediente.dni() + " no existe")
                            .build())
                    .build();
        }

        return Response.ok()
                .entity(updateExpediente)
                .build();
    }


    @DELETE
    @Path("/{cod}")
    public Response deleteExpediente(@PathParam("cod")String cod) throws AppException{
        Expediente expediente1 = findExpedienteByCodUseCase.execute(cod);
        if(expediente1 == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ExceptionDto.builder()
                            .withMessage("No existe el expediente con el codigo: " + cod)
                            .build())
                    .build();
        }

        String borrado = deleteExpedienteUseCase.execute(cod);
        return Response.noContent()
                .build();

    }
}
