/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.services;

import co.edu.unicundi.entity.ViewDocente;
import co.edu.unicundi.exception.ListNoContentException;
import co.edu.unicundi.exception.NoResponseBDException;
import co.edu.unicundi.exception.ObjectNotFoundException;
import co.edu.unicundi.interfaces.ILogicaViewDocente;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author cass465
 */
@Stateless
@Path("/viewDocentes")
public class ViewDocenteService {
    
    @EJB
    public ILogicaViewDocente logicaViewDocente;
    
    @Path("/listar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() throws ListNoContentException, NoResponseBDException {
        List<ViewDocente> docentes = logicaViewDocente.listar();
        return Response.status(Response.Status.OK).entity(docentes).build();
    }
    
    @Path("/obtenerPorId/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(
            //Campo id de url con validacion
            @NotNull(message = "Campo id requerido")
            @PathParam("id") Integer id) throws ObjectNotFoundException {

        ViewDocente docente = logicaViewDocente.obtenerPorId(id);
        return Response.status(Response.Status.OK).entity(docente).build();
    }
}
