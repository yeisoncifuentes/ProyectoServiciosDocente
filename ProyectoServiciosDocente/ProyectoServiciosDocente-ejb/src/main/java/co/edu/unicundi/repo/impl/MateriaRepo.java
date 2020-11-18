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
import javax.persistence.PersistenceContext;
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
    protected EntityManager getEntityManager() {
        return entity;
    }

    

}
