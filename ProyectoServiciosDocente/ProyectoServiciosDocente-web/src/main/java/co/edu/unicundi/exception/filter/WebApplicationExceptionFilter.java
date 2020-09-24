/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.exception.filter;

import co.edu.unicundi.POJO.ErrorWrapperPOJO;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Filtro que captura las excepciones que se disparan cuando hay un error en la
 * petición y no está filtrada
 *
 * @author Camilo Sanabria
 * @version 1.0.0
 */
@Provider
public class WebApplicationExceptionFilter implements ExceptionMapper<WebApplicationException> {

    @Override
    public Response toResponse(WebApplicationException ex) {
        System.out.println("Web Application Exception: " + ex.getClass().getCanonicalName());
        ex.printStackTrace();

        String descripcion = "Ha ocurrido un error, revisar la petición";
        String codigo = Integer.toString(ex.getResponse().getStatus());
        String codigoNombre = ex.getResponse().getStatusInfo().getReasonPhrase();

        ErrorWrapperPOJO error = new ErrorWrapperPOJO(descripcion, codigo, codigoNombre);
        return Response.status(Response.Status.fromStatusCode(ex.getResponse().getStatus())).entity(error).build();
    }

}
