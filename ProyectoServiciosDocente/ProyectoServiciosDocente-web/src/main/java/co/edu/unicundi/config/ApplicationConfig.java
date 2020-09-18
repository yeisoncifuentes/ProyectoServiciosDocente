/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author cass465
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResource.class);        
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ResourceListingProvider.class);
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(co.edu.unicundi.exception.filter.ConstraintViolationExceptionFilter.class);
        resources.add(co.edu.unicundi.exception.filter.ExceptionFilter.class);
        resources.add(co.edu.unicundi.exception.filter.ListNoContentExceptionFilter.class);
        resources.add(co.edu.unicundi.exception.filter.ObjectNotFoundExceptionFilter.class);
        resources.add(co.edu.unicundi.exception.filter.RegisteredObjectExceptionFilter.class);
        resources.add(co.edu.unicundi.exception.filter.WebApplicationExceptionFilter.class);
        resources.add(co.edu.unicundi.services.DocenteService.class);
    }
  
}
