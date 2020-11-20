/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.POJO;

import co.edu.unicundi.entity.Estudiante;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Clase que permite maquetar los atributos del docente
 *
 * @author Yeison Cifuentes
 * @version 1.0.0
 */
public class DocentePOJO implements Serializable {

    private Integer id;

    private String cedula;

    private String nombre;

    private String apellido;

    private String correo;

    private Date fechaNacimiento;

    private String fechaNacimientoFormato;

    private List<Estudiante> estudiantes;

    private boolean estado;

    private DireccionPOJO direccion;

    /**
     * Constructor vacio
     */
    public DocentePOJO() {

    }

    /**
     *
     * @param id
     * @param cedula
     * @param nombre
     * @param apellido
     * @param correo
     * @param fechaNacimiento
     * @param estudiantes
     * @param estado
     */
    public DocentePOJO(Integer id, String cedula, String nombre, String apellido, String correo, Date fechaNacimiento, List<Estudiante> estudiantes, boolean estado) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.estudiantes = estudiantes;
        this.estado = estado;
    }

    /**
     *
     * @param id
     * @param cedula
     * @param nombre
     * @param apellido
     * @param correo
     * @param fechaNacimiento
     * @param estudiantes
     * @param estado
     * @param direccion
     */
    public DocentePOJO(Integer id, String cedula, String nombre, String apellido, String correo, Date fechaNacimiento, List<Estudiante> estudiantes, boolean estado, DireccionPOJO direccion) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.estudiantes = estudiantes;
        this.estado = estado;
        this.direccion = direccion;
    }

    /**
     *
     * @param id
     * @param cedula
     * @param nombre
     * @param apellido
     * @param correo
     * @param fechaNacimiento
     * @param estado
     */
    public DocentePOJO(Integer id, String cedula, String nombre, String apellido, String correo, Date fechaNacimiento, boolean estado) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
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
     * @return the estudiantes
     */
    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    /**
     * @param estudiantes the estudiantes to set
     */
    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
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
     *
     * @return
     */
    public DireccionPOJO getDireccion() {
        return direccion;
    }

    /**
     *
     * @param direccion
     */
    public void setDireccion(DireccionPOJO direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the fechaNacimientoFormato
     */
    public String getFechaNacimientoFormato() {
        return fechaNacimientoFormato;
    }

    /**
     * @param fechaNacimientoFormato the fechaNacimientoFormato to set
     */
    public void setFechaNacimientoFormato(String fechaNacimientoFormato) {
        this.fechaNacimientoFormato = fechaNacimientoFormato;
    }

}
