package es.upsa.dasi.web.Adapters.input;


import Entities.Alumno;
import Exceptions.AppException;
import es.upsa.dasi.web.Adapters.input.dtos.Action;
import es.upsa.dasi.web.Adapters.input.dtos.AlumnoForm;
import es.upsa.dasi.web.Application.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.mvc.*;
import jakarta.mvc.binding.BindingResult;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

@Path("/alumnos")
@ApplicationScoped
public class AlumnosResource {

    @Inject
    MvcContext mvcContext;
    @Inject
    BindingResult bindingResult;
    @Inject
    Models models;
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
    @Path("/aviso/{aviso}")
    @Controller
    @UriRef("avisoController")
    @View("/jsps/aviso.jsp")
    public void aviso(@PathParam("aviso") String aviso){
        models.put("aviso",aviso);
    }


    @GET
    @Controller
    @UriRef("getAlumnos")
    @View("/jsps/alumnos.jsp")
    public void findAllAlumnos() throws AppException {
        List<Alumno> alumnos = findAllAlumnosUseCase.findAll();
        models.put("alumnos", alumnos);

    }


    @GET
    @Path("/{dni}")
    @Controller
    @UriRef("getAlumnoByDni")
    @View("/jsps/alumno.jsp")
    public Response findAlumnoByDni(@PathParam("dni") String dni) throws AppException {
        Alumno alumnoByDni = findAlumnoByDniUseCase.findAlumnoByDni(dni);
        if (alumnoByDni.dni() == null) {
            return Response.status(Response.Status.SEE_OTHER)
                    .location(mvcContext.uri("avisoController", Map.of("aviso",alumnoByDni.nombre())))
                    .build();
        }
        models.put("alumno", alumnoByDni);
        models.put("action", Action.SELECT);
        return null;
    }


    @POST
    @Controller
    @UriRef("addAlumno")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addAlumno(@Valid @BeanParam AlumnoForm alumnoForm) throws AppException {

        Alumno alumno = Alumno.builder()
                .withDni(alumnoForm.getDni())
                .withNombre(alumnoForm.getNombre())
                .withEdad(alumnoForm.getEdad())
                .withEmail(alumnoForm.getEmail())
                .build();


        Alumno alumnoNuevo = addAlumnoUseCase.addAlumno(alumno);
        if(alumnoNuevo.dni() == null) {
            return Response.status(Response.Status.SEE_OTHER)
                    .location(mvcContext.uri("avisoController", Map.of("aviso",alumnoNuevo.nombre())))
                    .build();
        }

        return Response.status(Response.Status.SEE_OTHER)
                .location(mvcContext.uri("getAlumnos"))
                .build();

    }


    @PUT
    @Path("/{dni}")
    @Controller
    @UriRef("updateAlumno")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateAlumno(@PathParam("dni")String dni, @Valid @BeanParam AlumnoForm alumnoForm) throws AppException {
        Alumno alumno = Alumno.builder()
                .withDni(dni)
                .withNombre(alumnoForm.getNombre())
                .withEdad(alumnoForm.getEdad())
                .withEmail(alumnoForm.getEmail())
                .build();

        Alumno alumnoActualizado = updateAlumnoUseCase.updateAlumno(alumno);

        if(alumnoActualizado.dni() == null) {
            return Response.status(Response.Status.SEE_OTHER)
                    .location(mvcContext.uri("avisoController", Map.of("aviso",alumnoActualizado.nombre())))
                    .build();
        }

        return Response.status(Response.Status.SEE_OTHER)
                .location(mvcContext.uri("getAlumnoByDni",Map.of("dni",alumnoActualizado.dni())))
                .build();
    }

    @DELETE
    @Path("/{dni}")
    @Controller
    @UriRef("deleteAlumno")
    public Response deleteAlumno(@PathParam("dni") String dni) throws AppException {
        String alumnoBorrado = deleteAlumnoUseCase.deleteAlumno(dni);
        if(alumnoBorrado == null) {
        return Response.status(Response.Status.SEE_OTHER)
                .location(mvcContext.uri("getAlumnos"))
                .build();
        }
        return Response.status(Response.Status.SEE_OTHER)
                .location(mvcContext.uri("avisoController", Map.of("aviso",alumnoBorrado)))
                .build();
    }


}
