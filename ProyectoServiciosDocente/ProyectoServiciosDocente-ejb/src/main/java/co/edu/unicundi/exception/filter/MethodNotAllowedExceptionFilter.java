/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.exception.filter;

import co.edu.unicundi.docentePOJO.ErrorWrapperPOJO;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author cass465
 */
@Provider
public class MethodNotAllowedExceptionFilter implements ExceptionMapper<NotAllowedException>{

    @Override
    public Response toResponse(NotAllowedException ex) {
        ErrorWrapperPOJO error = new ErrorWrapperPOJO(ex.getMessage(), "405", "Method Not Allowed");
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity(error).build();
    }
    
}
