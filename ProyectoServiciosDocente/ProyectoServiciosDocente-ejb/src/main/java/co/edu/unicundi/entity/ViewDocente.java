/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 *
 * @author Camilo Sanabria
 */
@Entity
@Immutable
@Table(name = "docentes.view_docente")
public class ViewDocente implements Serializable {
    @Id
    private Integer id;
    
    @Column(name = "cedula", nullable = false, unique = true, length = 10)
    private String cedula;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 30)
    private String apellido;

    @Column(name = "correo", nullable = false, unique = true)
    private String correo;    
      
    @Column(name = "estado", nullable = false)
    private boolean estado;

    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento; 
    
    @Column(name = "direccion", nullable = false, length = 70)
    private String direccion;
    
    @Column(name = "barrio", nullable = false, length = 25)
    private String barrio;
    
    @Column(name = "docente_id", nullable = false)
    private Integer docenteId;
    
    @Column(name = "estudiantes", nullable = false)
    private Integer estudiantes;

    public ViewDocente() {
    }

    public ViewDocente(Integer id, String cedula, String nombre, String apellido, String correo, boolean estado, Date fechaNacimiento, String direccion, String barrio, Integer docenteId, Integer estudiantes) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.estado = estado;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.barrio = barrio;
        this.docenteId = docenteId;
        this.estudiantes = estudiantes;
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
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the barrio
     */
    public String getBarrio() {
        return barrio;
    }

    /**
     * @param barrio the barrio to set
     */
    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    /**
     * @return the docenteId
     */
    public Integer getDocenteId() {
        return docenteId;
    }

    /**
     * @param docenteId the docenteId to set
     */
    public void setDocenteId(Integer docenteId) {
        this.docenteId = docenteId;
    }

    /**
     * @return the estudiantes
     */
    public Integer getEstudiantes() {
        return estudiantes;
    }

    /**
     * @param estudiantes the estudiantes to set
     */
    public void setEstudiantes(Integer estudiantes) {
        this.estudiantes = estudiantes;
    }
    
}
