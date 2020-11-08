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
import javax.persistence.PersistenceContext;
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

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<Estudiante> listar3() {
        TypedQuery<Estudiante> query = this.entity.createQuery("SELECT e FROM Estudiante e", Estudiante.class);
        return query.getResultList();
    }

}
