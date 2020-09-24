/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.exception.filter;

import co.edu.unicundi.POJO.ErrorWrapperPOJO;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Filtro que captura la excepcion que se dispara cuando ocurre un 404
 *
 * @author Camilo Sanabria
 * @version 1.0.0
 */
@Provider
public class NotFoundExceptionFilter implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException ex) {
        String descripcion = "El recurso solicitado no ha sido encontrado";
        String codigo = Integer.toString(ex.getResponse().getStatus());
        String codigoNombre = ex.getResponse().getStatusInfo().getReasonPhrase();

        ErrorWrapperPOJO error = new ErrorWrapperPOJO(descripcion, codigo, codigoNombre);
        return Response.status(Response.Status.NOT_FOUND).entity(error).build();
    }
}
