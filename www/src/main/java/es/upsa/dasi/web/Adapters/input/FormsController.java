package es.upsa.dasi.web.Adapters.input;

import Entities.Alumno;
import Entities.Expediente;
import Exceptions.AppException;
import es.upsa.dasi.web.Adapters.input.dtos.Action;
import es.upsa.dasi.web.Application.FindAlumnoByDniUseCase;
import es.upsa.dasi.web.Application.FindExpedienteByCodUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.mvc.*;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.util.Map;

@Path("/form")
@ApplicationScoped
public class FormsController {

    @Inject
    Models models;
    @Inject
    MvcContext mvcContext;
    @Inject
    FindAlumnoByDniUseCase findAlumnoByDniUseCase;
    @Inject
    FindExpedienteByCodUseCase findExpedienteByCodUseCase;

    @GET
    @Controller
    @UriRef("formAddAlumno")
    @View("/jsps/alumno.jsp")
    public void getFormAddAlumno(){
        models.put("action", Action.INSERT);
    }

    @GET
    @Path("/{dni}/updateAlumno")
    @Controller
    @UriRef("formUpdateAlumno")
    @View("/jsps/alumno.jsp")
    public Response getFormUpdateAlumno(@PathParam("dni")String dni) throws AppException {
        Alumno alumnoByDni = findAlumnoByDniUseCase.findAlumnoByDni(dni);
        if(alumnoByDni.dni()==null){
            return Response.status(Response.Status.SEE_OTHER)
                    .location(mvcContext.uri("avisoController", Map.of("aviso",alumnoByDni.nombre())))
                    .build();
        }
        models.put("alumno", alumnoByDni);
        models.put("action", Action.UPDATE);
        return null;
    }

    @GET
    @Path("/{dni}/borrarAlumno")
    @Controller
    @UriRef("formBorrarAlumno")
    @View("/jsps/alumno.jsp")
    public Response getFormBorrarAlumno(@PathParam("dni")String dni) throws AppException {
        Alumno alumnoByDni = findAlumnoByDniUseCase.findAlumnoByDni(dni);
        if(alumnoByDni.dni()==null){
            return Response.status(Response.Status.SEE_OTHER)
                    .location(mvcContext.uri("avisoController", Map.of("aviso",alumnoByDni.nombre())))
                    .build();
        }
        models.put("alumno", alumnoByDni);
        models.put("action", Action.DELETE);
        return null;
    }


    @GET
    @Path("/addExpediente")
    @Controller
    @UriRef("formAddExpediente")
    @View("/jsps/expediente.jsp")
    public void getFormAddExpediente(){
        models.put("action", Action.INSERT);
    }


    @GET
    @Path("/{cod}/updateExpediente")
    @Controller
    @UriRef("formUpdateExpediente")
    @View("/jsps/expediente.jsp")
    public Response getFormUpdateExpediente(@PathParam("cod")String cod) throws AppException {
        Expediente expedienteByCod = findExpedienteByCodUseCase.findExpedienteByCod(cod);
        if(expedienteByCod.cod()==null){
            return Response.status(Response.Status.SEE_OTHER)
                    .location(mvcContext.uri("avisoController", Map.of("aviso",expedienteByCod.dni())))
                    .build();
        }
        models.put("expediente", expedienteByCod);
        models.put("action", Action.UPDATE);
        return null;
    }

    @GET
    @Path("/{cod}/borrarExpediente")
    @Controller
    @UriRef("formDeleteExpediente")
    @View("/jsps/expediente.jsp")
    public Response getFormBorrarExpediente(@PathParam("cod")String cod) throws AppException {
        Expediente expedienteByCod = findExpedienteByCodUseCase.findExpedienteByCod(cod);
        if(expedienteByCod.cod()==null){
            return Response.status(Response.Status.SEE_OTHER)
                    .location(mvcContext.uri("avisoController", Map.of("aviso",expedienteByCod.dni())))
                    .build();
        }
        models.put("expediente", expedienteByCod);
        models.put("action", Action.DELETE);
        return null;
    }



}
