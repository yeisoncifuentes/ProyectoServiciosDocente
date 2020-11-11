/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.repo.impl;

import co.edu.unicundi.entity.Docente;
import co.edu.unicundi.entity.DocenteMateria;
import co.edu.unicundi.repo.AbstractFacade;
import co.edu.unicundi.repo.IDocenteMateriaRepo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author YEISON
 */
@Stateless
public class DocenteMateriaRepo implements IDocenteMateriaRepo {

    @PersistenceContext(unitName = "edu.unicundi_ProyectoServiciosDocente-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager entity;

    @Override
    public List<DocenteMateria> listarDocenteMateria(Integer idDocente) {       
        TypedQuery<DocenteMateria> query = this.entity.createQuery("SELECT d FROM DocenteMateria d WHERE d.docente.id = ?1", DocenteMateria.class);;
        query.setParameter(1, idDocente);
        return query.getResultList();
    }

    @Override
    public void guardar(DocenteMateria docenteMateria) {
        entity.persist(docenteMateria);
    }

}
