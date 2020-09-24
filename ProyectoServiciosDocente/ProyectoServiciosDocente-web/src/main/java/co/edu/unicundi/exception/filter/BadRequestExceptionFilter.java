/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.exception.filter;

import co.edu.unicundi.POJO.ErrorWrapperPOJO;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Filtro que captura la excepcion que se dispara cuando ocurre un 400
 *
 * @author Camilo Sanabria
 * @version 1.0.0
 */
@Provider
public class BadRequestExceptionFilter implements ExceptionMapper<BadRequestException> {

    @Override
    public Response toResponse(BadRequestException ex) {
        String descripcion = "Ha ocurrido un error, revise la petición";
        String codigo = Integer.toString(ex.getResponse().getStatus());
        String codigoNombre = ex.getResponse().getStatusInfo().getReasonPhrase();

        ErrorWrapperPOJO error = new ErrorWrapperPOJO(descripcion, codigo, codigoNombre);
        return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    }
}
