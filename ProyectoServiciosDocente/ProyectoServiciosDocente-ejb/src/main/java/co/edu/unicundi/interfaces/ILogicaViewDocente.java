/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.interfaces;

import co.edu.unicundi.entity.ViewDocente;
import co.edu.unicundi.exception.ListNoContentException;
import co.edu.unicundi.exception.ObjectNotFoundException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cass465
 */
@Local
public interface ILogicaViewDocente {
    
    public List<ViewDocente> listar() throws ListNoContentException;
    
    public ViewDocente obtenerPorId(int id) throws ObjectNotFoundException;
}
