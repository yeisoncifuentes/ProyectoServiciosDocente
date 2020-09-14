/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.exception.filter;

import co.edu.unicundi.docentePOJO.ErrorWrapperPOJO;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author cass465
 */
@Provider
public class ConstraintViolationExceptionFilter implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException ex) {
        String erroresValidacion = "";

        for (ConstraintViolation cv : ex.getConstraintViolations()) {
            erroresValidacion += cv.getMessage() + ", ";
        }

        ErrorWrapperPOJO error = new ErrorWrapperPOJO(erroresValidacion, "400", "Bad Request");
        return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    }

}
