/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.logica;

import co.edu.unicundi.BD.DAODocente;
import co.edu.unicundi.POJO.DocentePOJO;
import co.edu.unicundi.exception.IdRequiredException;
import co.edu.unicundi.exception.ListNoContentException;
import co.edu.unicundi.exception.ObjectNotFoundException;
import co.edu.unicundi.exception.RegisteredObjectException;
import co.edu.unicundi.interfaces.ILogicaDocente;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 * Clase que permite hacer la logica de los servicios docente
 *
 * @author Yeison Cifuentes
 * @version 1.0.0
 */
@Stateless
//Le asigna las transacciones a este bean y no al conenedor con el fin de que si hay un error, este bean acabe con la transaccion
@TransactionManagement(TransactionManagementType.BEAN)
public class LogicaDocente implements ILogicaDocente {

    /**
     * Registra el docente especificado
     *
     * @param docente
     * @throws RegisteredObjectException
     */
    @Override
    public void registrar(DocentePOJO docente) throws RegisteredObjectException {
        List<DocentePOJO> docentes = new DAODocente().obtenerPorCedulaYCorreo(docente.getCedula(), docente.getCorreo());

        if (docentes.size() == 0) {
            new DAODocente().registrar(docente);
        } else {
            throw new RegisteredObjectException("La cedula y/o el correo del docente ya existen");
        }
    }

    /**
     * Lista todos los docentes registrados
     *
     * @return Lista de docentes
     * @throws ObjectNotFoundException
     */
    @Override
    public List<DocentePOJO> listar() throws ObjectNotFoundException {
        ArrayList<DocentePOJO> docentes = new DAODocente().listar();
        if (docentes.size() > 0) {
            return docentes;
        } else {
            throw new ListNoContentException();
        }
    }

    /**
     * Obtiene un docente filtrado por la cedula especificada
     *
     * @param cedula
     * @return Docente filtrado
     * @throws ObjectNotFoundException
     */
    @Override
    public DocentePOJO obtenerPorCedula(String cedula) throws ObjectNotFoundException {
        DocentePOJO docente = new DAODocente().obtenerPorCedula(cedula);
        if (docente.getId() > 0) {
            return docente;
        } else {
            throw new ObjectNotFoundException("La cedula ingresada no existe");
        }
    }

    /**
     * Edita los datos del docente especificado
     *
     * @param docente
     * @throws RegisteredObjectException
     * @throws ObjectNotFoundException
     * @throws IdRequiredException
     */
    @Override
    public void editar(DocentePOJO docente) throws RegisteredObjectException, ObjectNotFoundException, IdRequiredException {
        if (docente.getId() != 0) {
            DocentePOJO docenteFiltradoId = new DAODocente().obtenerPorId(docente.getId());

            if (docenteFiltradoId.getId() == docente.getId()) {

                List<DocentePOJO> docentes = new DAODocente().obtenerPorCedulaYCorreo(docente.getCedula(), docente.getCorreo());

                if (docentes.size() == 0) {
                    new DAODocente().editar(docente);
                } else if (docentes.size() == 1 && docentes.get(0).getId() == docente.getId()) {
                    new DAODocente().editar(docente);
                } else {
                    throw new RegisteredObjectException("La cedula y/o el correo del docente ya existen");
                }
            } else {
                throw new ObjectNotFoundException("El id del docente no existe");
            }
        } else {
            throw new IdRequiredException("Campo id requerido");
        }
    }

    /**
     * Lista los docentes con la materia especificada
     *
     * @param materia
     * @return Lista de docentes
     * @throws ObjectNotFoundException
     */
    @Override
    public List<DocentePOJO> obtenerDocentesMateria(String materia) throws ObjectNotFoundException {
        List<DocentePOJO> todosDocentes = new DAODocente().listar();

        List<DocentePOJO> docentesMateria = new ArrayList();

        for (DocentePOJO docente : todosDocentes) {
            for (String materiaDocente : docente.getMaterias()) {

                if (materiaDocente.equals(materia)) {
                    System.out.println(materiaDocente);
                    docentesMateria.add(docente);
                }
            }
        }

        if (docentesMateria.size() > 0) {
            return docentesMateria;
        } else {
            throw new ObjectNotFoundException("La materia ingresada no existe");
        }
    }

    /**
     * Elimina un docente de acuerdo al id especificado
     *
     * @param id
     * @throws ObjectNotFoundException
     */
    @Override
    public void eliminar(int id) throws ObjectNotFoundException {
        DocentePOJO docente = new DAODocente().obtenerPorId(id);
        if (docente.getId() != 0) {
            new DAODocente().eliminar(id);
        } else {
            throw new ObjectNotFoundException("El id del docente no existe");
        }
    }
}
