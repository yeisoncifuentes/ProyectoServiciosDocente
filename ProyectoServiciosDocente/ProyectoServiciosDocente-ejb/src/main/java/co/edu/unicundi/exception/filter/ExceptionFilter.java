/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.exception.filter;

import co.edu.unicundi.docentePOJO.ErrorWrapperPOJO;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author cass465
 */
@Provider
public class ExceptionFilter implements ExceptionMapper<Exception>{

    @Override
    public Response toResponse(Exception ex) {
        System.out.println("Excepcion: " + ex.getClass().getCanonicalName());
        ErrorWrapperPOJO error = new ErrorWrapperPOJO(ex.getLocalizedMessage(), "500", "Internal Server Error");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
    }
    
}
