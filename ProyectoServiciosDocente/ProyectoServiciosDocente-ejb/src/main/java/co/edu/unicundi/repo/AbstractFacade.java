/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.repo;

import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;

/**
 *
 * @author cass465
 */
public abstract class AbstractFacade<T, V> {

    private Class<T> entityClass;
    
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    protected abstract EntityManager getEntityManager();
    
    public void registrar(T entity) {
        getEntityManager().persist(entity);
    }

    public List<T> listar() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public void editar(T entity) {
        getEntityManager().merge(entity);
    }

    public void eliminar(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public T obtenerPorId(V id) {
        return getEntityManager().find(entityClass, id);
    }
}
