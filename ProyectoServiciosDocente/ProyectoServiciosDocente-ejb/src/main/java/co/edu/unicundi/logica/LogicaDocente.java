/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.logica;

import co.edu.unicundi.BD.DAODocente;
import co.edu.unicundi.docentePOJO.DocentePOJO;
import co.edu.unicundi.exception.ObjectNotFoundException;
import co.edu.unicundi.exception.RegisteredObjectException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import utilitarios.ClaseValidator;

/**
 * Clase que permite hacer la logica de los servicios
 *
 * @author YEISON
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
    public List<DocentePOJO> listar() {
        return new DAODocente().listar();
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
    public void editar(DocentePOJO docente) throws Exception {
        DocentePOJO docenteFiltradoId = new DAODocente().obtenerPorId(docente.getId());

        ClaseValidator validar = new ClaseValidator().validarDocente(docente);

        if (validar.isEstado()) {
            if (docenteFiltradoId.getId() == docente.getId()) {

                List<DocentePOJO> docentes = new DAODocente().obtenerPorCedulaYCorreo(docente.getCedula(), docente.getCorreo());

                if (docentes.size() == 0) {
                    new DAODocente().editar(docente);
                } else if (docentes.size() == 1 && docentes.get(0).getId() == docente.getId()) {
                    new DAODocente().editar(docente);
                } else {
                    throw new Exception("La cedula o el correo del docente ya existen");
                }
            } else {
                throw new Exception("El docente no existe");
            }
        } else {
            throw new Exception(validar.getMensaje());
        }

    }

    /**
     * Obtener los docentes de la materia ingresada
     *
     * @param materia
     * @return
     */
    public List<DocentePOJO> obtenerDocentesMateria(String materia) throws Exception {
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
            throw new Exception("No existen docentes con la materia " + materia);
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
