/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.interfaces;

import co.edu.unicundi.POJO.DocentePOJO;
import co.edu.unicundi.POJO.EstudiantePOJO;
import co.edu.unicundi.entity.Estudiante;
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
public interface ILogicaEstudiante {

    public void registrar(Estudiante estudiante) throws IdRequiredException, ObjectNotFoundException,RegisteredObjectException;

    public List<EstudiantePOJO> listar() throws ListNoContentException, NoResponseBDException;

    public Estudiante obtenerPorId(int id) throws ObjectNotFoundException;

    public void bloquear(int id) throws ObjectNotFoundException, NoResponseBDException;

    public void habilitar(int id) throws ObjectNotFoundException, NoResponseBDException;

    public void eliminar(int id) throws ObjectNotFoundException, NoResponseBDException;

    public void editar(Estudiante estudiante) throws RegisteredObjectException, ObjectNotFoundException, IdRequiredException, NoResponseBDException;

}
