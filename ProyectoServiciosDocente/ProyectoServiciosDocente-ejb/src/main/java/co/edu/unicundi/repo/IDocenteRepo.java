/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.repo;

import co.edu.unicundi.entity.Docente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cass465
 */
@Local
public interface IDocenteRepo {

    /**
     * Registra el docente especificado
     *
     * @param docente
     */
    public void registrar(Docente docente);

    /**
     * Lista todos los docentes registrados con sus respectivos estudiantes
     *
     * @return Lista de docentes
     */
    public List<Docente> listar();

    /**
     * Lista todos los docentes registrados para query que no tenga estudiantes
     *
     * @return Lista de docentes
     */
    public List<Docente> listar2();

    /**
     * Lista todos los docentes registrados con sus respectivos estudiantes y
     * cancelando transacción
     *
     * @return Lista de docentes
     */
    public List<Docente> listar3();

    /**
     * Lista todos los docentes registrados sin los estudiantes
     *
     * @return Lista de docentes
     */
    public List<Docente> listarNoEstudiantes();

    /**
     * Edita los datos del docente especificado
     *
     * @param docente
     */
    public void editar(Docente docente);

    /**
     * Elimina el docente de acuerdo especificado
     *
     * @param docente
     */
    public void eliminar(Docente docente);

    /**
     * Obtiene el docente filtrado por el id especificado
     *
     * @param id
     * @return Docente correspondiente al id
     */
    public Docente obtenerPorId(Integer id);

    /**
     * Obtiene un docente filtrado por la cedula especificada
     *
     * @param cedula
     * @return Docente filtrado
     */
    public Docente obtenerPorCedula(String cedula);

    /**
     * Obtiene un docente filtrado por el correo especificada
     *
     * @param correo
     * @return Docente filtrado
     */
    public Docente obtenerPorCorreo(String correo);

    /**
     * Lista los docentes con la materia especificada
     *
     * @param materia
     * @return Lista de docentes
     */
    public List<Docente> listarPorMateria(String materia);

}
