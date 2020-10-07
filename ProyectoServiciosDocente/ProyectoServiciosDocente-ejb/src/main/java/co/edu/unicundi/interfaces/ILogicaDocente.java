/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.interfaces;

import co.edu.unicundi.POJO.DocentePOJO;
import co.edu.unicundi.entity.Docente;
import co.edu.unicundi.exception.IdRequiredException;
import co.edu.unicundi.exception.ListNoContentException;
import co.edu.unicundi.exception.NoResponseBDException;
import co.edu.unicundi.exception.ObjectNotFoundException;
import co.edu.unicundi.exception.RegisteredObjectException;
import java.io.IOException;
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
     * @throws NoResponseBDException
     */
    public void registrar(Docente docente);
    
    /**
     * Registra el docente especificado dentro del fichero
     *
     * @param docente
     * @throws IOException
     * @throws NoResponseBDException
     */
    public void registrarFichero(DocentePOJO docente) throws IOException, NoResponseBDException;
    
    /**
     * Lista todos los docentes registrados
     *
     * @return Lista de docentes
     * @throws ListNoContentException
     * @throws NoResponseBDException
     */
    public List<Docente> listar() throws ListNoContentException, NoResponseBDException;
    
    
     /**
     * Lista todos los docentes registrados
     *
     * @return Lista de docentes
     * @throws IOException
     * @throws NoResponseBDException
     */
    public List<DocentePOJO> listarFichero() throws IOException, NoResponseBDException;
    
    
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
    public void editar(DocentePOJO docente) throws RegisteredObjectException, ObjectNotFoundException, IdRequiredException, NoResponseBDException;
    
    /**
     * Lista los docentes con la materia especificada
     *
     * @param materia
     * @return Lista de docentes
     * @throws ObjectNotFoundException
     * @throws NoResponseBDException
     */
    public List<DocentePOJO> obtenerDocentesMateria(String materia) throws ObjectNotFoundException, NoResponseBDException;
    
    /**
     * Elimina un docente de acuerdo al id especificado
     *
     * @param id
     * @throws ObjectNotFoundException
     * @throws NoResponseBDException
     */
    public void eliminar(int id) throws ObjectNotFoundException, NoResponseBDException;
    
    
}
