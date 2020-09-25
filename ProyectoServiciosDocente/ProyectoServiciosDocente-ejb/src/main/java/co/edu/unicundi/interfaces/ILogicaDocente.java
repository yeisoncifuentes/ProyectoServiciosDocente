/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.interfaces;

import co.edu.unicundi.POJO.DocentePOJO;
import co.edu.unicundi.exception.IdRequiredException;
import co.edu.unicundi.exception.ObjectNotFoundException;
import co.edu.unicundi.exception.RegisteredObjectException;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface que contiene los metodos de logica docente
 * 
 * @author Camilo Sanabria
 * @version 1.0.0
 */
@Local
public interface ILogicaDocente {

    /**
     * Registra el docente especificado
     *
     * @param docente
     * @throws RegisteredObjectException
     */
    public void registrar(DocentePOJO docente) throws RegisteredObjectException;
    
    /**
     * Lista todos los docentes registrados
     *
     * @return Lista de docentes
     * @throws ObjectNotFoundException
     */
    public List<DocentePOJO> listar() throws ObjectNotFoundException;
    
    /**
     * Obtiene un docente filtrado por la cedula especificada
     *
     * @param cedula
     * @return Docente filtrado
     * @throws ObjectNotFoundException
     */
    public DocentePOJO obtenerPorCedula(String cedula) throws ObjectNotFoundException;
    
    /**
     * Edita los datos del docente especificado
     *
     * @param docente
     * @throws RegisteredObjectException
     * @throws ObjectNotFoundException
     * @throws IdRequiredException
     */
    public void editar(DocentePOJO docente) throws RegisteredObjectException, ObjectNotFoundException, IdRequiredException;
    
    /**
     * Lista los docentes con la materia especificada
     *
     * @param materia
     * @return Lista de docentes
     * @throws ObjectNotFoundException
     */
    public List<DocentePOJO> obtenerDocentesMateria(String materia) throws ObjectNotFoundException;
    
    /**
     * Elimina un docente de acuerdo al id especificado
     *
     * @param id
     * @throws ObjectNotFoundException
     */
    public void eliminar(int id) throws ObjectNotFoundException;
}
