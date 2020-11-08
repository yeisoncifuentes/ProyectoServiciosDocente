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
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Entidad direccion de la BD
 * @author YEISON
 */
@Entity
@Table(name = "docentes.tbl_direccion")
public class Direccion implements Serializable {

    @Id    
    private Integer id;    
    
    @NotNull(message = "Dirección requerida")
    @Size(min = 2, max = 70, message = "Longitud de direccion no valido")
    @Column(name = "direccion", nullable = false, length = 70)
    private String direccion;
    
    @NotNull(message = "Barrio requerido")
    @Size(min = 2, max = 25, message = "Longitud de barrio no valido")
    @Column(name = "barrio", nullable = false, length = 25)
    private String barrio;
    
    @OneToOne
    @MapsId
    @JsonIgnore
    private Docente docente;

    public Direccion() {
    }

    public Direccion(Integer id, String direccion, String barrio, Docente docente) {
        this.id = id;
        this.direccion = direccion;
        this.barrio = barrio;
        this.docente = docente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

        

}
