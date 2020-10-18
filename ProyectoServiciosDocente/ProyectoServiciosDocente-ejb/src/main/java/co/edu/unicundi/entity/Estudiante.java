/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author YEISON
 */
@Entity
@Table(name = "estudiante.tbl_estudiante")
public class Estudiante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Campo nombre requerido")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Formato de nombre incorrecto")
    @Size(min = 2, max = 30, message = "Longitud de nombre no valido")
    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @NotNull(message = "Campo apellido requerido")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Formato de apellido incorrecto")
    @Size(min = 2, max = 30, message = "Longitud de apellido no valido")
    @Column(name = "apellido", nullable = false, length = 30)
    private String apellido;
    
    @ManyToOne
    @JoinColumn(name = "id_docente", nullable = false)
    private Docente docente;

    public Estudiante() {
    }    
    
    public Estudiante(Integer id, String nombre, String apellido, Docente docente) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.docente = docente;
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

     @JsonIgnore
    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }
    
    
}
