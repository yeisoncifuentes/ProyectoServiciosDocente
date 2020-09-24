/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.exception.filter;

import co.edu.unicundi.exception.ListNoContentException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Filtro que captura la excepcion que se dispara cuando una lista se devuelve
 * vacia
 *
 * @author Camilo Sanabria
 * @version 1.0.0
 */
@Provider
public class ListNoContentExceptionFilter implements ExceptionMapper<ListNoContentException> {

    @Override
    public Response toResponse(ListNoContentException ex) {
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
