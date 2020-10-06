/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Contiene la entidad docente de BD
 * 
 * @author Camilo Sanabria
 * @version 1.0.0
 */
@Entity
@Table(name = "docente.tbl_docente")
public class Docente implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "cedula", nullable = false)
    private String cedula;
    
    @Column(name = "materias")
    private List<String> materias;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "apellido", nullable = false)
    private String apellido;
    
    @Column(name = "correo", nullable = false)
    private String correo;

    public Docente() {
    }

    public Docente(String cedula, List<String> materias, String nombre, String apellido, String correo) {
        this.cedula = cedula;
        this.materias = materias;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the materias
     */
    public List<String> getMaterias() {
        return materias;
    }

    /**
     * @param materias the materias to set
     */
    public void setMaterias(List<String> materias) {
        this.materias = materias;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
}
