/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.repo.impl;

import co.edu.unicundi.entity.Materia;
import co.edu.unicundi.repo.AbstractFacade;
import co.edu.unicundi.repo.IMateriaRepo;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author YEISON
 */
@Stateless
public class MateriaRepo extends AbstractFacade<Materia, Integer> implements IMateriaRepo {

    @PersistenceContext(unitName = "edu.unicundi_ProyectoServiciosDocente-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager entity;

    public MateriaRepo() {
        super(Materia.class);
    }

    public MateriaRepo(Class<Materia> entityClass) {
        super(entityClass);
    }

    @Override
    public Materia obtenerPorNombre(String nombre) {
        try {
            Query query = entity.createQuery("SELECT m FROM Materia m WHERE m.nombre = ?1", Materia.class);
            query.setParameter(1, nombre);

            return (Materia) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return entity;
    }

    @Override
    public List<Materia> listarNoAsociadas(Integer idDocente) {
        TypedQuery<Materia> query = entity.createQuery("SELECT m FROM Materia m EXCEPT "
                + "SELECT m FROM Materia m, DocenteMateria dm "
                + "WHERE m.id = dm.materia.id AND dm.docente.id = ?1", Materia.class);
        query.setParameter(1, idDocente);
        return query.getResultList();
    }
}
