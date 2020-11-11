/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.repo;

import co.edu.unicundi.entity.DocenteMateria;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author YEISON
 */
@Local
public interface IDocenteMateriaRepo {
    
     public void guardar(DocenteMateria docenteMateria);
    
    public List<DocenteMateria> listarDocenteMateria(Integer idDocente);
    
}
