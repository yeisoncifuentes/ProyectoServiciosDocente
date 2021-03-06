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
     * @throws RegisteredObjectException
     * @throws NoResponseBDException
     */
    public void registrar(Docente docente) throws RegisteredObjectException, NoResponseBDException;
    
    /**
     * Lista todos los docentes registrados
     *
     * @return Lista de docentes
     * @throws ListNoContentException
     * @throws NoResponseBDException
     */
    public List<Docente> listar() throws ListNoContentException, NoResponseBDException;
    
    /**
     * Obtiene un docente filtrado por la cedula especificada
     *
     * @param cedula
     * @return Docente filtrado
     * @throws ObjectNotFoundException
     * @throws NoResponseBDException
     */
    public Docente obtenerPorCedula(String cedula) throws ObjectNotFoundException, NoResponseBDException;
    
    /**
     * Edita los datos del docente especificado
     *
     * @param docente
     * @throws RegisteredObjectException
     * @throws ObjectNotFoundException
     * @throws IdRequiredException
     * @throws NoResponseBDException
     */
    public void editar(Docente docente) throws RegisteredObjectException, ObjectNotFoundException, IdRequiredException, NoResponseBDException;
    
    /**
     * Lista los docentes con la materia especificada
     *
     * @param materia
     * @return Lista de docentes
     * @throws ObjectNotFoundException
     * @throws NoResponseBDException
     */
    public List<Docente> obtenerDocentesMateria(String materia) throws ObjectNotFoundException, NoResponseBDException;
    
    /**
     * Elimina un docente de acuerdo al id especificado
     *
     * @param id
     * @throws ObjectNotFoundException
     * @throws NoResponseBDException
     */
    public void eliminar(int id) throws ObjectNotFoundException, NoResponseBDException;
}
