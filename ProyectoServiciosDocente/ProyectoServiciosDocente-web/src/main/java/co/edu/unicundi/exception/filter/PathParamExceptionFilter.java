/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.exception.filter;

import co.edu.unicundi.POJO.ErrorWrapperPOJO;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.glassfish.jersey.server.ParamException.PathParamException;

/**
 * Filtro que captura la excepcion que se dispara cuando un parametro url
 * anotado con @PathParam está mal enviado por el cliente
 *
 * @author Camilo Sanabria
 * @version 1.0.0
 */
@Provider
public class PathParamExceptionFilter implements ExceptionMapper<PathParamException> {

    @Override
    public Response toResponse(PathParamException ex) {
        String descripcion = "Formato de parametro " + ex.getParameterName() + " incorrecto";
        String codigo = Integer.toString(ex.getResponse().getStatus());
        String codigoNombre = ex.getResponse().getStatusInfo().getReasonPhrase();

        ErrorWrapperPOJO error = new ErrorWrapperPOJO(descripcion, codigo, codigoNombre);
        return Response.status(Response.Status.fromStatusCode(ex.getResponse().getStatus())).entity(error).build();
    }

}
