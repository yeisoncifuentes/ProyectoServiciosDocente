/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.exception.filter;

import co.edu.unicundi.docentePOJO.ErrorWrapperPOJO;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author cass465
 */
@Provider
public class WebApplicationExceptionFilter implements ExceptionMapper<WebApplicationException>{

    @Override
    public Response toResponse(WebApplicationException ex) {
        ErrorWrapperPOJO error = new ErrorWrapperPOJO(ex.getMessage(), Integer.toString(ex.getResponse().getStatus()), ex.getResponse().getStatusInfo().getReasonPhrase());
        return Response.status(Response.Status.fromStatusCode(ex.getResponse().getStatus())).entity(error).build();
    }
    
}
