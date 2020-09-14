/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.exception.filter;

import co.edu.unicundi.docentePOJO.ErrorWrapperPOJO;
import co.edu.unicundi.exception.RegisteredObjectException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author cass465
 */
@Provider
public class RegisteredObjectExceptionFilter implements ExceptionMapper<RegisteredObjectException>{

    @Override
    public Response toResponse(RegisteredObjectException ex) {
        ErrorWrapperPOJO error = new ErrorWrapperPOJO(ex.getMessage(), "400", "Bad Request");
        return Response.status(Response.Status.FORBIDDEN).entity(error).build();
    }
    
}
