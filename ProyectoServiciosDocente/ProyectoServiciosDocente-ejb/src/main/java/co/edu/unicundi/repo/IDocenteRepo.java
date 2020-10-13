/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.repo;

import co.edu.unicundi.entity.Docente;
import co.edu.unicundi.exception.IdRequiredException;
import co.edu.unicundi.exception.ListNoContentException;
import co.edu.unicundi.exception.NoResponseBDException;
import co.edu.unicundi.exception.ObjectNotFoundException;
import co.edu.unicundi.exception.RegisteredObjectException;
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
     * Lista todos los docentes registrados
     *
     * @return Lista de docentes
     */
    public List<Docente> listar();
    
    /**
     * Obtiene un docente filtrado por la cedula especificada
     *
     * @param cedula
     * @return Docente filtrado
     */
    public Docente obtenerPorCedula(String cedula);
    
    /**
     * Obtiene un lista con los docentes que ya tienen la cedula 
     *
     * @param cedula
     * @return Docente filtrado
     */
    public List<Docente> validarCedula(String cedula);
    
    /**
     * Obtiene un lista con los docentes que ya tienen el correo
     *
     * @param correo
     * @return Docente filtrado
     */
    public List<Docente> validarCorreo(String correo);
    
    /**
     * Edita los datos del docente especificado
     *
     * @param docente
     */
    public void editar(Docente docente);
    
    /**
     * Lista los docentes con la materia especificada
     *
     * @param materia
     * @return Lista de docentes
     */
    public List<Docente> obtenerDocentesMateria(String materia);
    
    /**
     * Obtiene el docente filtrado por el id especificado
     * 
     * @param id
     * @return Docente correspondiente al id
     */
    public Docente obtenerPorId(Integer id);
    
    /**
     * Elimina el docente de acuerdo especificado
     *
     * @param docente
     */
    public void eliminar(Docente docente);

}
