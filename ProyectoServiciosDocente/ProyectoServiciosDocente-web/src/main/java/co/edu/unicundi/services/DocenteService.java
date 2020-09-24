/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.services;

import co.edu.unicundi.docentePOJO.DocentePOJO;
import co.edu.unicundi.logica.LogicaDocente;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.util.List;
import javax.ejb.Stateless;
import javax.validation.Valid;
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
 * Controlaor de servicios docente
 *
 * @author Yeison Cifuentes
 * @version 1.0.0
 */
@Stateless
@Path("/docentes")
@Api(value = "/docentes", description = "Manejo de datos docente")
public class DocenteService {

    /**
     * Servicio que registra un docente
     *
     * @param docente
     * @return Response con docente creado
     */
    @POST
    @Path("/registrar")
    @ApiOperation(value = "Registra un docente en el sistema")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "El docente fue registrado correctamente, se devuelve el objeto creado"),
        @ApiResponse(code = 400, message = " Error en la petición, los campos enviados en la solicitud son inválidos o el\n"
                + "correo o la cédula ya están registrados o si la petición no contiene body"),
        @ApiResponse(code = 404, message = "Recurso no encontrado"),
        @ApiResponse(code = 405, message = "El método de la solicitud no es POST"),
        @ApiResponse(code = 515, message = "El tipo de cuerpo en la petición no es Json"),
        @ApiResponse(code = 500, message = "Error en el servidor o base de datos")})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrar(@Valid DocentePOJO docente) {
        new LogicaDocente().registrar(docente);
        return Response.status(Response.Status.CREATED).entity(docente).build();
    }

    /**
     * Servicio que lista todos los docentes
     *
     * @return Response con lista de docentes
     */
    @Path("/listar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Lista todos los docentes registrados")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Los docentes se obtuvieron correctamente"),
        @ApiResponse(code = 204, message = "La lista de docentes está vacía"),
        @ApiResponse(code = 400, message = "Error en la petición, puede suceder si se envía body"),
        @ApiResponse(code = 404, message = "Recurso no encontrado"),
        @ApiResponse(code = 405, message = "El método de la solicitud no es GET"),
        @ApiResponse(code = 500, message = "Error en el servidor o base de datos")})
    public Response listar() {
        List<DocentePOJO> docentes = new LogicaDocente().listar();
        return Response.status(Response.Status.OK).entity(docentes).build();
    }

    /**
     * Servicio que obtiene un docente filtrado por cedula
     *
     * @param cedula
     * @return Response con docente filtrado
     */
    @Path("/obtenerPorCedula/{cedula}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Obtiene un docente registrado filtrando por cedula")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "El docente filtrado se obtuvo correctamente"),
        @ApiResponse(code = 400, message = "Error en la petición, puede suceder si se envía body"),
        @ApiResponse(code = 404, message = "Recurso o docente no encontrado"),
        @ApiResponse(code = 405, message = "El método de la solicitud no es GET"),
        @ApiResponse(code = 500, message = "Error en el servidor o base de datos")})
    public Response obtenerPorCedula(@PathParam("cedula") String cedula) throws Exception {
        DocentePOJO docente = new LogicaDocente().obtenerPorCedula(cedula);
        return Response.status(Response.Status.OK).entity(docente).build();
    }

    /**
     * Servicio que lista todos los docentes que tengan la materia especificada
     *
     * @param materia
     * @return Response con lista de docentes filtrados
     */
    @Path("/obtenerDocentesMateria/{materia}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Lista todos los docentes filtrando por materia")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Los docentes se obtienen correctamente"),
        @ApiResponse(code = 400, message = "Error en la petición, puede suceder si se envía body"),
        @ApiResponse(code = 404, message = "Recurso encontrado o lista vacia"),
        @ApiResponse(code = 405, message = "El método de la solicitud no es GET"),
        @ApiResponse(code = 500, message = "Error en el servidor o base de datos")})
    public Response obtenerDocentesMateria(@PathParam("materia") String materia) throws Exception {
        List<DocentePOJO> docentes = new LogicaDocente().obtenerDocentesMateria(materia);
        return Response.status(Response.Status.OK).entity(docentes).build();
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
    @ApiOperation(value = "Edita los datos de un docente en el sistema")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Se editaron los datos correctamente"),
        @ApiResponse(code = 400, message = " Error en la petición, los campos enviados en la solicitud son inválidos o el\n"
                + "correo o la cédula ya están registrados o si la petición no contiene body"),
        @ApiResponse(code = 404, message = "Recurso o docente no encontrado"),
        @ApiResponse(code = 405, message = "El método de la solicitud no es PUT"),
        @ApiResponse(code = 515, message = "El tipo de cuerpo en la petición no es Json"),
        @ApiResponse(code = 500, message = "Error en el servidor o base de datos")})
    public Response editar(@Valid DocentePOJO docente) {
        new LogicaDocente().editar(docente);
        return Response.status(Response.Status.OK).entity("Editado correctamente").build();
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
    @ApiOperation(value = "Elimina un docente registrado con el id especificado")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "El docente se eliminó correctamente"),
        @ApiResponse(code = 400, message = "Error en la petición, puede suceder sí se envía body"),
        @ApiResponse(code = 404, message = "Recurso o docente no encontrado"),
        @ApiResponse(code = 405, message = "El método de la solicitud no es DELETE"),
        @ApiResponse(code = 500, message = "Error en el servidor o base de datos")})
    public Response eliminar(@PathParam("id") int id) {
        new LogicaDocente().eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
