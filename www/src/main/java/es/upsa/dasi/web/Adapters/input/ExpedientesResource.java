package es.upsa.dasi.web.Adapters.input;

import Entities.Expediente;
import Exceptions.AppException;
import es.upsa.dasi.web.Adapters.input.dtos.Action;
import es.upsa.dasi.web.Adapters.input.dtos.ExpedienteForm;
import es.upsa.dasi.web.Application.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.mvc.*;
import jakarta.mvc.binding.BindingResult;
import jakarta.mvc.binding.ParamError;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;
import java.util.stream.Collectors;

@Path("/expedientes")
@ApplicationScoped
public class ExpedientesResource {

    @Inject
    MvcContext mvcContext;
    @Inject
    BindingResult bindingResult;
    @Inject
    Models models;
    @Inject
    FindExpedienteByDniUseCase findExpedienteByDniUseCase;
    @Inject
    FindExpedienteByCodUseCase findExpedienteByCodUseCase;
    @Inject
    AddExpedienteUseCase addExpedienteUseCase;
    @Inject
    UpdateExpedienteUseCase updateExpedienteUseCase;
    @Inject
    DeleteExpedienteUseCase deleteExpedienteUseCase;

    @GET
    @Path("/{dni}")
    @Controller
    @UriRef("getExpedienteByDni")
    @View("/jsps/expediente.jsp")
    public Response getExpedienteByDni(@PathParam("dni")String dni) throws AppException {
        Expediente expedienteByDni = findExpedienteByDniUseCase.findExpedienteByDni(dni);
        if(expedienteByDni.cod()==null){
            return Response.status(Response.Status.SEE_OTHER)
                    .location(mvcContext.uri("avisoController", Map.of("aviso",expedienteByDni.dni())))
                    .build();
        }
        models.put("expediente", expedienteByDni);
        models.put("action", Action.SELECT);
        return null;
    }

    @POST
    @Controller
    @UriRef("addExpediente")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addExpediente(@Valid @BeanParam ExpedienteForm expedienteForm) throws AppException {
        Expediente expediente = Expediente.builder()
                .withCod(null)
                .withDni(expedienteForm.getDni())
                .withTitulacion(expedienteForm.getTitulacion())
                .withCredSup(expedienteForm.getCredSup())
                .withNotaMedia(expedienteForm.getNotaMedia())
                .build();

        if(bindingResult.isFailed()){
            Map<String,String> errores = bindingResult.getAllErrors()
                    .stream()
                    .collect(Collectors.toMap(ParamError::getParamName, ParamError::getMessage));

            models.put("expediente",expediente);
            models.put("action", Action.INSERT);
            models.put("errores",errores);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("/jsps/expediente.jsp")
                    .build();
        }

        Expediente addExpediente = addExpedienteUseCase.addExpediente(expediente);

        if(addExpediente.cod()==null){
            return Response.status(Response.Status.SEE_OTHER)
                    .location(mvcContext.uri("avisoController", Map.of("aviso",addExpediente.dni())))
                    .build();
        }

        return Response.status(Response.Status.SEE_OTHER)
                .location(mvcContext.uri("getAlumnos"))
                .build();
    }

    @PUT
    @Controller
    @Path("/{cod}/expedientesCod")
    @UriRef("updateExpedienteCod")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateExpediente(@PathParam("cod") String cod, @Valid @BeanParam ExpedienteForm expedienteForm) throws AppException {
        Expediente expediente = Expediente.builder()
                .withCod(cod)
                .withDni(expedienteForm.getDni())
                .withTitulacion(expedienteForm.getTitulacion())
                .withCredSup(expedienteForm.getCredSup())
                .withNotaMedia(expedienteForm.getNotaMedia())
                .build();

        Expediente expedienteByDni = findExpedienteByDniUseCase.findExpedienteByDni(expediente.dni());
        if(expedienteByDni.cod()==null){
            return Response.status(Response.Status.SEE_OTHER)
                    .location(mvcContext.uri("avisoController", Map.of("aviso",expedienteByDni.dni())))
                    .build();
        }

        if(bindingResult.isFailed()){
            Map<String,String> errores = bindingResult.getAllErrors()
                    .stream()
                    .collect(Collectors.toMap(ParamError::getParamName, ParamError::getMessage));

            models.put("expediente",expediente);
            models.put("action", Action.UPDATE);
            models.put("errores",errores);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("/jsps/expediente.jsp")
                    .build();
        }

        Expediente updateExpediente = updateExpedienteUseCase.updateExpediente(expediente);

        if(updateExpediente.cod()==null){
            return Response.status(Response.Status.SEE_OTHER)
                    .location(mvcContext.uri("avisoController", Map.of("aviso",updateExpediente.dni())))
                    .build();
        }
        return Response.status(Response.Status.SEE_OTHER)
                .location(mvcContext.uri("getAlumnos"))
                .build();

    }

    @DELETE
    @Path("/{cod}/borrarExpediente")
    @UriRef("deleteExpediente")
    @Controller
    public Response deleteExpediente(@PathParam("cod") String cod) throws AppException {
        String deleteExpediente = deleteExpedienteUseCase.deleteExpediente(cod);
        if(deleteExpediente!=null){
            return Response.status(Response.Status.SEE_OTHER)
                    .location(mvcContext.uri("avisoController", Map.of("aviso",deleteExpediente)))
                    .build();
        }
        return Response.status(Response.Status.SEE_OTHER)
                .location(mvcContext.uri("getAlumnos"))
                .build();
    }





}
