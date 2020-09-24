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
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Clase que permite hacer la logica de los servicios docente
 *
 * @author Yeison Cifuentes
 * @version 1.0.0
 */
@Stateless
public class LogicaDocente {

    /**
     * Registrar docente
     *
     * @param docente
     */
    public void registrar(DocentePOJO docente) throws RegisteredObjectException {
        List<DocentePOJO> docentes = new DAODocente().obtenerPorCedulaYCorreo(docente.getCedula(), docente.getCorreo());

        if (docentes.size() == 0) {
            new DAODocente().registrar(docente);
        } else {
            throw new RegisteredObjectException("La cedula o el correo del docente ya existen");
        }
    }

    /**
     * Listar todos los docentes
     *
     * @return
     */
    public List<DocentePOJO> listar() throws ObjectNotFoundException {
        ArrayList<DocentePOJO> docentes = new DAODocente().listar();
        if (docentes.size() > 0) {
            return docentes;
        } else {
            throw new ListNoContentException();
        }
    }

    /**
     * Obtener un docente filtrado por cedula
     *
     * @param cedula
     * @return
     */
    public DocentePOJO obtenerPorCedula(String cedula) throws ObjectNotFoundException {
        DocentePOJO docente = new DAODocente().obtenerPorCedula(cedula);
        if (docente.getId() > 0) {
            return docente;
        } else {
            throw new ObjectNotFoundException("La cedula no existe");
        }
    }

    /**
     * Editar los datos del docente
     *
     * @param docente
     */
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
                    throw new RegisteredObjectException("La cedula o el correo del docente ya existen");
                }
            } else {
                throw new ObjectNotFoundException("El id del docente no existe");
            }
        } else {
            throw new IdRequiredException("Campo id requerido");
        }
    }

    /**
     * Obtener los docentes de la materia ingresada
     *
     * @param materia
     * @return
     */
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
            throw new ObjectNotFoundException("La materia no existe");
        }
    }

    /**
     * Eliminar docente
     *
     * @param id
     */
    public void eliminar(int id) throws ObjectNotFoundException {
        DocentePOJO docente = new DAODocente().obtenerPorId(id);
        if (docente.getId() != 0) {
            new DAODocente().eliminar(id);
        } else {
            throw new ObjectNotFoundException("El id del docente no existe");
        }
    }
}
