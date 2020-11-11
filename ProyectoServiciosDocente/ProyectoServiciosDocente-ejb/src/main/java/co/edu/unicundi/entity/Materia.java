/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Contiene la entidad materia de BD
 *
 * @author Camilo Sanabria
 * @version 1.0.0
 */
@Entity
@Table(name = "docentes.tbl_materia")
public class Materia implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull(message = "Campo nombre requerido")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Formato de nombre incorrecto")
    @Size(min = 2, max = 30, message = "Longitud de nombre no valido")
    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;
    
  
   

    public Materia() {
    }

    public Materia(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
      
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

   
    
}
