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
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Servicios
 *
 * @author YEISON
 */
@javax.enterprise.context.RequestScoped
@Path("/docentes")
public class docenteServicio {

    /**
     * LIsta todos los docentes 
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
     * Lista todos los docentes que tengas la materia ingresada
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
     * @param docente
     * @return 
     */
    @Path("/editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(DocentePOJO docente) {

        new LogicaDocente().editar(docente);
        return Response.status(Response.Status.OK).entity("Editado correctamente").build();
    }

}
