/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.interfaces;


import co.edu.unicundi.POJO.DocenteMateriaPOJO;
import co.edu.unicundi.POJO.MateriaPOJO;
import co.edu.unicundi.entity.Materia;
import co.edu.unicundi.exception.IdRequiredException;
import co.edu.unicundi.exception.ListNoContentException;
import co.edu.unicundi.exception.NoResponseBDException;
import co.edu.unicundi.exception.ObjectNotFoundException;
import co.edu.unicundi.exception.RegisteredObjectException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author YEISON
 */
@Local
public interface ILogicaMateria {

    public void registrar(Materia materia) throws RegisteredObjectException, NoResponseBDException, IdRequiredException;

     public List<MateriaPOJO> listar() throws ListNoContentException, NoResponseBDException;
    
    public Materia obtenerPorId(int id) throws ObjectNotFoundException;

    public void bloquear(int id) throws ObjectNotFoundException, NoResponseBDException;

    public void habilitar(int id) throws ObjectNotFoundException, NoResponseBDException;

    public void eliminar(int id) throws ObjectNotFoundException, NoResponseBDException, RegisteredObjectException;

    public void editar(Materia materia) throws RegisteredObjectException, ObjectNotFoundException, IdRequiredException, NoResponseBDException;

    public List<MateriaPOJO> listarNoAsociadas(Integer idDocente) throws ObjectNotFoundException, ListNoContentException, NoResponseBDException;
}
