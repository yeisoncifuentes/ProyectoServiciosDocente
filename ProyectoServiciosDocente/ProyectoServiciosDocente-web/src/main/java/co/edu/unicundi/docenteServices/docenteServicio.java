/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.docenteServices;

import co.edu.unicundi.docentePOJO.DocentePOJO;
import co.edu.unicundi.logica.LogicaDocente;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
import utilitarios.ClaseValidator;

/**
 * Servicios
 *
 * @author YEISON
 */
@Stateless
@Path("/docentes")
public class docenteServicio {

    /**
     * Servicio para registrar docente
     *
     * @param docente
     * @return
     */
    @POST
    @Path("/registrar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrar(DocentePOJO docente) {

        try {
            ClaseValidator validar = new ClaseValidator().validarDocente(docente);

            if (validar.isEstado()) {
                new LogicaDocente().registrar(docente);
                return Response.status(Response.Status.OK).entity("Registrado correctamente").build();

            } else {
                return Response.status(Response.Status.OK).entity(validar.getMensaje()).build();

            }

        } catch (Exception e) {

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("error en el servidor").build();
        }

    }

    /**
     * LIsta todos los docentes
     *
     * @return
     */
    @Path("/listar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() {
        List<DocentePOJO> docentes = new ArrayList();
        docentes = new LogicaDocente().listar();
        return Response.status(Response.Status.OK).entity(docentes).build();
    }

    /**
     * Servicio para obtener un docente filtrado por cedula
     *
     * @param cedula
     * @return
     */
    @Path("/obtenerPorCedula/{cedula}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorCedula(@PathParam("cedula") String cedula) {
        DocentePOJO docente = new LogicaDocente().obtenerPorCedula(cedula);
        return Response.status(Response.Status.OK).entity(docente).build();
    }

    /**
     * Lista todos los docentes que tengan la materia ingresada
     *
     * @param materia
     * @return
     */
    @Path("/obtenerDocentesMateria/{materia}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerDocentesMateria(@PathParam("materia") String materia) {
        List<DocentePOJO> docentes = new ArrayList();
        docentes = new LogicaDocente().obtenerDocentesMateria(materia);
        return Response.status(Response.Status.OK).entity(docentes).build();
    }

    /**
     * Servicio que permite editar los datos del docente
     *
     * @param docente
     * @return
     */
    @Path("/editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(DocentePOJO docente) {
        try {
            ClaseValidator validar = new ClaseValidator().validarDocente(docente);

            if (validar.isEstado()) {
                new LogicaDocente().editar(docente);
                return Response.status(Response.Status.OK).entity("Editado correctamente").build();

            } else {
                return Response.status(Response.Status.OK).entity(validar.getMensaje()).build();

            }

        } catch (Exception e) {

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("error en el servidor").build();
        }
    }

    /**
     * Servicio para eliminar un docente
     *
     * @param id
     * @return
     */
    @Path("/eliminar/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") int id) {
        new LogicaDocente().eliminar(id);
        return Response.status(Response.Status.OK).entity("Eliminado correctamente").build();
    }
}
