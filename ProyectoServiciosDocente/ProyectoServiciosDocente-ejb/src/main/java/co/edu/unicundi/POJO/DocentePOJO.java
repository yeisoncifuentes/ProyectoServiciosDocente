/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.POJO;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Clase que permite maquetar los atributos del docente
 *
 * @author Yeison Cifuentes
 * @version 1.0.0
 */
public class DocentePOJO implements Serializable {

    /**
     * Id del docente
     */
    private int id;

    /**
     * cedula del docente
     */
    @NotNull(message = "Campo cedula requerido")
    @Pattern(regexp = "^([0-9])*$", message = "Formato de cedula incorrecto, indicar solamente valores numéricos")
    @Size(min = 7, max = 10, message = "La longitud de la cedula debe estar entre 7 y 10")
    private String cedula;

    /**
     * Lista de materias del docente
     */
    private List<String> materias;

    /**
     * Nombre del docente
     */
    @NotNull(message = "Campo nombre requerido")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Formato de nombre incorrecto")
    @Size(min = 2, max = 30, message = "Longitud de nombre no valido")
    private String nombre;

    /**
     * Apellido del docente
     */
    @NotNull(message = "Campo apellido requerido")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Formato de apellido incorrecto")
    @Size(min = 2, max = 30, message = "Longitud de apellido no valido")
    private String apellido;

    /**
     * Correo electronico del docente
     */
    @NotNull(message = "Campo correo requerido")
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@+[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Formato de correo incorrecto")
    private String correo;

    /**
     * Constructor vacio
     */
    public DocentePOJO() {

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
        this.correo = correo;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
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
