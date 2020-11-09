/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.repo.impl;

import co.edu.unicundi.entity.ViewDocente;
import co.edu.unicundi.repo.IViewDocenteRepo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author cass465
 */
@Stateless
public class ViewDocenteRepo implements IViewDocenteRepo{

    @PersistenceContext(unitName = "edu.unicundi_ProyectoServiciosDocente-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager entity;
    
    @Override
    public List<ViewDocente> listar() {
        TypedQuery<ViewDocente> query = this.entity.createQuery("SELECT vd FROM ViewDocente vd", ViewDocente.class);
        return query.getResultList();
    }

    @Override
    public ViewDocente obtenerPorId(int id) {
        return entity.find(ViewDocente.class, id);
    }
    
}
