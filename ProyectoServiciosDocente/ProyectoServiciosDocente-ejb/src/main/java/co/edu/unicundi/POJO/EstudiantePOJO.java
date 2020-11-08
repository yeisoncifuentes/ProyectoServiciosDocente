/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.POJO;

import co.edu.unicundi.entity.Docente;
import java.io.Serializable;

/**
 *
 * @author YEISON
 */
public class EstudiantePOJO implements Serializable {

    private Integer id;

    private String nombre;

    private String apellido;

    private Docente docente;

    /**
     * Constructo vacio
     */
    public EstudiantePOJO() {
    }

    /**
     * 
     * @param id
     * @param nombre
     * @param apellido
     * @param docente 
     */
    public EstudiantePOJO(Integer id, String nombre, String apellido, Docente docente) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.docente = docente;
    }

    /**
     * 
     * @param id
     * @param nombre
     * @param apellido 
     */
    public EstudiantePOJO(Integer id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }
    
    

}
