/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.repo;


import co.edu.unicundi.entity.Estudiante;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author YEISON
 */
@Local
public interface IEstudianteRepo {

    public void registrar(Estudiante estudiante);

    public List<Estudiante> listar3();

    public void editar(Estudiante estudiante);
    
    public void eliminar(Estudiante estudiante);
    
    public Estudiante obtenerPorId(Integer id);  

}
