/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import co.edu.unicundi.docentePOJO.DocentePOJO;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author YEISON
 */
public class ClaseValidator {

    /**
     * Estado para conocer el tipo de retorno
     */
    private boolean estado;

    /**
     * Mensaje con las anotaciones personalizadas
     */
    private String mensaje;

    public ClaseValidator(boolean estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public ClaseValidator() {

    }

    public ClaseValidator validarDocente(DocentePOJO docente) {
        ClaseValidator validar = new ClaseValidator();
        validar.mensaje = "Error en ";

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<DocentePOJO>> errores = validator.validate(docente);
        for (ConstraintViolation<DocentePOJO> error : errores) {
            validar.mensaje += "Campo: " + error.getPropertyPath() + " -> mensaje Error: " + error.getMessage() + "\n";
        }
        
        if (errores.isEmpty()) {
            validar.estado = true;
        } else {
            validar.estado = true;

        }
        return validar;

    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
