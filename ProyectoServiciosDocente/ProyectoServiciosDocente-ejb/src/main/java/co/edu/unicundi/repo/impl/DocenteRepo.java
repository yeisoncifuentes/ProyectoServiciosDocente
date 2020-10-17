/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.repo.impl;

import co.edu.unicundi.entity.Docente;
import co.edu.unicundi.repo.IDocenteRepo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author cass465
 */
@Stateless
public class DocenteRepo implements IDocenteRepo {

    @PersistenceContext(unitName = "edu.unicundi_ProyectoServiciosDocente-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager entity;

    @Override
    public void registrar(Docente docente) {
        entity.persist(docente);
    }

    @Override
    public List<Docente> listar() {
        TypedQuery<Docente> query = this.entity.createQuery("SELECT d FROM Docente d", Docente.class);
        return query.getResultList();
    }

    @Override
    public void editar(Docente docente) {
        entity.merge(docente);
    }

    @Override
    public void eliminar(Docente docente) {
        entity.remove(docente);
    }

    @Override
    public Docente obtenerPorId(Integer id) {
        return entity.find(Docente.class, id);
    }

    @Override
    public Docente obtenerPorCedula(String cedula) {
        try {
            Query query = entity.createQuery("SELECT d FROM Docente d WHERE d.cedula = ?1", Docente.class);
            query.setParameter(1, cedula);

            return (Docente) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Docente obtenerPorCorreo(String correo) {
        try {
            Query query = entity.createQuery("SELECT d FROM Docente d WHERE d.correo = ?1", Docente.class);
            query.setParameter(1, correo);

            return (Docente) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Docente> listarPorMateria(String materia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
