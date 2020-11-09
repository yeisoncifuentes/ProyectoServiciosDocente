/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.repo;

import co.edu.unicundi.entity.ViewDocente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cass465
 */
@Local
public interface IViewDocenteRepo {
    
    public List<ViewDocente> listar();
    
    public ViewDocente obtenerPorId(int id);
    
}
