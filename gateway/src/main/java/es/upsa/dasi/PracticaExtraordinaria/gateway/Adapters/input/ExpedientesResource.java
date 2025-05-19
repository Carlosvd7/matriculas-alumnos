package es.upsa.dasi.PracticaExtraordinaria.gateway.Adapters.input;

import Entities.ExceptionDto;
import Entities.Expediente;
import Exceptions.AppException;
import es.upsa.dasi.PracticaExtraordinaria.gateway.Adapters.input.dtos.ExpedienteDto;
import es.upsa.dasi.PracticaExtraordinaria.gateway.Application.*;
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
public class ExpedientesResource {

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
      Expediente expediente = findExpedienteByCodUseCase.findByCod(cod);
      if(expediente.cod() == null){
         return Response.status(Response.Status.NOT_FOUND)
                 .entity(ExceptionDto.builder()
                         .withMessage(expediente.dni())
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
      Expediente expediente = findExpedienteByDniUseCase.findExpedienteByDni(dni);
      if(expediente.cod() == null){
         return Response.status(Response.Status.NOT_FOUND)
                 .entity(ExceptionDto.builder()
                         .withMessage(expediente.dni())
                         .build())
                 .build();
      }
      return Response.ok()
              .entity(expediente)
              .build();
   }

   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Response addExpediente(ExpedienteDto expedienteDto) throws AppException {
      Expediente expediente = Expediente.builder()
              .withCod(null)
              .withDni(expedienteDto.getDni())
              .withNotaMedia(expedienteDto.getNotaMedia())
              .withCredSup(expedienteDto.getCredSup())
              .withTitulacion(expedienteDto.getTitulacion())
              .build();

      Expediente expedienteDni = findExpedienteByDniUseCase.findExpedienteByDni(expedienteDto.getDni());
      if(expedienteDni.cod() != null){
         return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                 .entity(ExceptionDto.builder()
                         .withMessage("El alumno ya tiene un expediente")
                         .build())
                 .build();
      }

      Expediente expediente1 = addExpedienteUseCase.addExpediente(expediente);

      if(expediente1.cod()==null){
         return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                 .entity(ExceptionDto.builder()
                         .withMessage(expediente1.dni())
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

      Expediente expediente1 = findExpedienteByCodUseCase.findByCod(cod);
      if(expediente1.cod() == null){
         return Response.status(Response.Status.NOT_FOUND)
                 .entity(ExceptionDto.builder()
                         .withMessage("El expediente con el codigo: " + cod + " no existe")
                         .build())
                 .build();
      }

      Expediente expedienteDni = findExpedienteByDniUseCase.findExpedienteByDni(expediente.dni());
      if(expedienteDni != null && !expediente1.dni().equals(expedienteDni.dni())){
         return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                 .entity(ExceptionDto.builder()
                         .withMessage("El alumno ya tiene un expediente")
                         .build())
                 .build();
      }


      Expediente updateExpediente = updateExpedienteUseCase.updateExpediente(expediente);
      if(updateExpediente.cod()==null){
         return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                 .entity(ExceptionDto.builder()
                         .withMessage(updateExpediente.dni())
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
      Expediente expediente = findExpedienteByCodUseCase.findByCod(cod);
      if(expediente.cod() == null){
         return Response.status(Response.Status.NOT_FOUND)
                 .entity(ExceptionDto.builder()
                         .withMessage(expediente.dni())
                         .build())
                 .build();
      }

      String borrado = deleteExpedienteUseCase.deleteExpediente(cod);
      return Response.noContent()
              .build();

   }
}
