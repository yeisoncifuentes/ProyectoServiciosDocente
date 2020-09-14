/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.exception.filter;

import co.edu.unicundi.docentePOJO.ErrorWrapperPOJO;
import co.edu.unicundi.exception.ObjectNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author cass465
 */
@Provider
public class ObjectNotFoundExceptionFilter implements ExceptionMapper<ObjectNotFoundException>{

    @Override
    public Response toResponse(ObjectNotFoundException ex) {
        ErrorWrapperPOJO error = new ErrorWrapperPOJO(ex.getMessage(), "404", "Not Found");
        return Response.status(Response.Status.NOT_FOUND).entity(error).build();
    }
    
}