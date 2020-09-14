/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.docentePOJO;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author cass465
 */
public class ErrorWrapperPOJO implements Serializable{
    
    private String error;
    private String codigo;
    private String codigoNombre;
    private String fecha;

    public ErrorWrapperPOJO(String error, String codigo, String codigoNombre) {
        this.error = error;
        this.codigo = codigo;
        this.codigoNombre = codigoNombre;
        this.fecha = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(new Date());
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoNombre() {
        return codigoNombre;
    }

    public void setCodigoNombre(String codigoNombre) {
        this.codigoNombre = codigoNombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
