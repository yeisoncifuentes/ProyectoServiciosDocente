/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.exception.filter;

import co.edu.unicundi.docentePOJO.ErrorWrapperPOJO;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author cass465
 */
@Provider
public class BadRequestExceptionFilter implements ExceptionMapper<BadRequestException>{

    @Override
    public Response toResponse(BadRequestException ex) {
        ErrorWrapperPOJO error = new ErrorWrapperPOJO(ex.getMessage(), "400", "Bad Request");
        return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    }
    
}
