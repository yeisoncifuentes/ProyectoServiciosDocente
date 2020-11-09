/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.logica;

import co.edu.unicundi.entity.ViewDocente;
import co.edu.unicundi.exception.ListNoContentException;
import co.edu.unicundi.exception.ObjectNotFoundException;
import co.edu.unicundi.interfaces.ILogicaViewDocente;
import co.edu.unicundi.repo.IViewDocenteRepo;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author cass465
 */
@Stateless
public class LogicaViewDocente implements ILogicaViewDocente {

    @EJB
    private IViewDocenteRepo repo;
    
    @Override
    public List<ViewDocente> listar() throws ListNoContentException {
        try {
            List<ViewDocente> docentes = new ArrayList();

            docentes = repo.listar();
            if (docentes.size() > 0) {
                return docentes;
            } else {
                throw new ListNoContentException();
            }

        } catch (ListNoContentException ex) {
            throw new ListNoContentException();
        }
    }

    @Override
    public ViewDocente obtenerPorId(int id) throws ObjectNotFoundException {
        try {
            ViewDocente docente = repo.obtenerPorId(id);
            if (docente != null) {
                return docente;
            } else {
                throw new ObjectNotFoundException("El id ingresado no existe");
            }
        } catch (ObjectNotFoundException ex) {
            throw new ObjectNotFoundException(ex.getMessage());
        }
    }
    
}
