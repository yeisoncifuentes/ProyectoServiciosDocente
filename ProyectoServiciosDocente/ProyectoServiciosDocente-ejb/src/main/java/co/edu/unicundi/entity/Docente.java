/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

    @NotNull(message = "Campo cedula requerido")
    @Pattern(regexp = "^([0-9])*$", message = "Formato de cedula incorrecto, indicar valores numéricos sin espacios")
    @Size(min = 7, max = 10, message = "La longitud de la cedula debe estar entre 7 y 10")
    @Column(name = "cedula", nullable = false)
    private String cedula;

    @Column(name = "materias")
    private List<String> materias;

    @NotNull(message = "Campo nombre requerido")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Formato de nombre incorrecto")
    @Size(min = 2, max = 30, message = "Longitud de nombre no valido")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull(message = "Campo apellido requerido")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Formato de apellido incorrecto")
    @Size(min = 2, max = 30, message = "Longitud de apellido no valido")
    @Column(name = "apellido", nullable = false)
    private String apellido;

    @NotNull(message = "Campo correo requerido")
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@+[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Formato de correo incorrecto")
    @Column(name = "correo", nullable = false)
    private String correo;

    @NotNull(message = "Campo fecha requerido")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    public Docente() {
    }

    public Docente(Integer id, String cedula, List<String> materias, String nombre, String apellido, String correo, Date fechaNacimiento) {
        this.id = id;
        this.cedula = cedula;
        this.materias = materias;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
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

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fechaNacimiento;
    }

    /**
     * @param fecha the fechaVincucion to set
     */
    public void setFecha(Date fecha) {
        this.fechaNacimiento = fecha;
    }

}
