/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.repo.impl;

import co.edu.unicundi.entity.Estudiante;
import co.edu.unicundi.repo.AbstractFacade;
import co.edu.unicundi.repo.IEstudianteRepo;
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
public class EstudianteRepo extends AbstractFacade<Estudiante, Integer> implements IEstudianteRepo {

    @PersistenceContext(unitName = "edu.unicundi_ProyectoServiciosDocente-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager entity;

    public EstudianteRepo() {
        super(Estudiante.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entity;
    }

    @Override
    public Estudiante obtenerNombreApellido(String nombre, String apellido) {
         try {
        TypedQuery<Estudiante> query = entity.createQuery("SELECT e fROM Estudiante e WHERE e.nombre = ?1 AND e.apellido = ?2", Estudiante.class);
        query.setParameter(1, nombre);
        query.setParameter(2, apellido);
        return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

}
