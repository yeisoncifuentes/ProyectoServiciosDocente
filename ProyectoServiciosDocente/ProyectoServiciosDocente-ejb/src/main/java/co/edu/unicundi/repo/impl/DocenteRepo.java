/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.repo.impl;

import co.edu.unicundi.entity.Docente;
import co.edu.unicundi.exception.IdRequiredException;
import co.edu.unicundi.exception.ListNoContentException;
import co.edu.unicundi.exception.NoResponseBDException;
import co.edu.unicundi.exception.ObjectNotFoundException;
import co.edu.unicundi.exception.RegisteredObjectException;
import co.edu.unicundi.repo.IDocenteRepo;
import java.util.ArrayList;
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
public class DocenteRepo implements IDocenteRepo {
    
    @PersistenceContext(unitName = "edu.unicundi_ProyectoServiciosDocente-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager entity;

    @Override
    public void registrar(Docente docente)  {
        this.entity.persist(docente);
    }

    @Override
    public List<Docente> listar() throws ListNoContentException, NoResponseBDException {
        TypedQuery<Docente> query = this.entity.createQuery("SELECT d FROM Docente d", Docente.class);
        
        List<Docente> docentes = query.getResultList();
        
        return docentes;
    }

    @Override
    public Docente obtenerPorCedula(String cedula) throws ObjectNotFoundException, NoResponseBDException {
        TypedQuery<Docente> query = entity.createQuery("SELECT d FROM Docente d WHERE d.cedula = ?1", Docente.class);
        query.setParameter(1, cedula);
        
        List<Docente> resultado = query.getResultList();
        
        Docente docente = new Docente();
        
        for (Docente result : resultado) {
            docente = new Docente(result.getId(), result.getCedula(), result.getMateria(), result.getNombre(), result.getApellido(), result.getCorreo());
        }

        return docente;
    }

    @Override
    public void editar(Docente docente) throws RegisteredObjectException, ObjectNotFoundException, IdRequiredException, NoResponseBDException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Docente> obtenerDocentesMateria(String materia) throws ObjectNotFoundException, NoResponseBDException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(int id) throws ObjectNotFoundException, NoResponseBDException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
