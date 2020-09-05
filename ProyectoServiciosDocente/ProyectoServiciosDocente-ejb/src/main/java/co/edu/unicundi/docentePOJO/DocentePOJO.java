/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.docentePOJO;

import java.io.Serializable;
import java.util.List;

/**
 *Clase que permite maquetar los atributos del docente
 * @author YEISON
 */
public class DocentePOJO implements Serializable {

    /**
     * Id del docente
     */
    int id;

    /**
     * cedula del docente
     */
    String cedula;

    /**
     * Lista de materias del docente
     */
    List<String> materias;

    /**
     * Nombre del docente
     */
    String nombre;

    /**
     * Apellido del docente
     */
    String apellido;
    
    /**
     * Correo electronico del docente
     */
    String correo;

    /**
     * Constructor vacio
     */
    public DocentePOJO(){
        
    }
    
    /**
     * Constructor de la clase
     *
     * @param id
     * @param cedula
     * @param materias
     * @param nombre
     * @param apellido
     */
    public DocentePOJO(int id, String cedula, List<String> materias, String nombre, String apellido, String correo) {
        this.id = id;
        this.cedula = cedula;
        this.materias = materias;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo=correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public List<String> getMaterias() {
        return materias;
    }

    public void setMaterias(List<String> materias) {
        this.materias = materias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
        
        
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
