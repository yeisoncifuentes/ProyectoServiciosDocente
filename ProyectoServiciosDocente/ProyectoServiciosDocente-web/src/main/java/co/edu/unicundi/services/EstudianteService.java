/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.services;


import co.edu.unicundi.POJO.EstudiantePOJO;
import co.edu.unicundi.entity.Estudiante;
import co.edu.unicundi.exception.IdRequiredException;
import co.edu.unicundi.exception.ListNoContentException;
import co.edu.unicundi.exception.NoResponseBDException;
import co.edu.unicundi.exception.ObjectNotFoundException;
import co.edu.unicundi.exception.RegisteredObjectException;
import co.edu.unicundi.interfaces.ILogicaEstudiante;
import com.wordnik.swagger.annotations.Api;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author YEISON
 */
@Stateless
@Path("/estudiantes")
@Api(value = "/estudiantes", description = "Manejo de datos estudiantes")
public class EstudianteService {

    @EJB
    public ILogicaEstudiante logicaEstudiante;

    /**
     * Servicio que registra un estudiante
     *
     * @param docente
     * @return Response con docente creado
     */
    @POST
    @Path("/registrar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrar(@Valid Estudiante estudiante) throws RegisteredObjectException, NoResponseBDException, IdRequiredException, ObjectNotFoundException {
        logicaEstudiante.registrar(estudiante);
        return Response.status(Response.Status.CREATED).entity("Estudiante creado correctamente").build();
    }

    @Path("/listar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() throws ListNoContentException, NoResponseBDException {
        List<EstudiantePOJO> estudiantes = logicaEstudiante.listar();
        return Response.status(Response.Status.OK).entity(estudiantes).build();
    }

    /**
     * Servicio que obtiene un docente filtrado por id
     *
     * @param id
     * @return Response con docente filtrado
     */
    @Path("/obtenerPorId/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(
            //Campo id de url con validacion
            @NotNull(message = "Campo id requerido")
            @PathParam("id") Integer id) throws ObjectNotFoundException {

        Estudiante estudiante = logicaEstudiante.obtenerPorId(id);
        return Response.status(Response.Status.OK).entity(estudiante).build();
    }

    /**
     * Servicio que edita los datos del docente especificado
     *
     * @param docente
     * @return Response
     */
    @Path("/editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@Valid Estudiante estudiante) throws RegisteredObjectException, ObjectNotFoundException, IdRequiredException, NoResponseBDException {
        logicaEstudiante.editar(estudiante);
        return Response.status(Response.Status.OK).entity("Estudiante editado correctamente").build();
    }

    /**
     * Servicio que elimina un docente
     *
     * @param id
     * @return Response
     */
    @Path("/eliminar/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(
            //Campo id url con validacion
            @NotNull(message = "Campo id requerido")
            @PathParam("id") Integer id) throws ObjectNotFoundException, NoResponseBDException {
        logicaEstudiante.eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
