/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.docentePOJO;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Clase que permite maquetar los atributos del docente
 *
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
    @NotNull(message = "Campo cedula requerido")
    @Pattern(regexp = "^([0-9])*$")
    @Size(min = 7, max = 10, message = "formato de cedula incorrecto")
    String cedula;

    /**
     * Lista de materias del docente
     */
    List<String> materias;

    /**
     * Nombre del docente
     */
    @NotNull(message = "Campo nombre requerido")
    @Pattern(regexp = "^[a-zA-Z ]*$")
    @Size(min = 2, max = 30, message = "Formato de nombre incorrecto")
    String nombre;

    /**
     * Apellido del docente
     */
    @NotNull(message = "Campo apellido requerido")
    @Pattern(regexp = "^[a-zA-Z ]*$")
    @Size(min = 2, max = 30, message = "Formato de apellido incorrecto")
    String apellido;

    /**
     * Correo electronico del docente
     */
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@+[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Formato de correo incorrecto")
    String correo;

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
