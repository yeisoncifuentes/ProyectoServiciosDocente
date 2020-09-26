/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.exception.filter;

import co.edu.unicundi.POJO.ErrorWrapperPOJO;
import co.edu.unicundi.exception.NoResponseBDException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Filtro que captura la excepcion que se dispara cuando la BD no responde
 *
 * @author Camilo Sanabria
 * @version 1.0.0
 */
@Provider
public class NoResponseBDExceptionFilter implements ExceptionMapper<NoResponseBDException> {

    @Override
    public Response toResponse(NoResponseBDException ex) {
        String descripcion = ex.getMessage();
        String codigo = "500";
        String codigoNombre = "Internal Server Error";

        ErrorWrapperPOJO error = new ErrorWrapperPOJO(descripcion, codigo, codigoNombre);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
    }
    
}
