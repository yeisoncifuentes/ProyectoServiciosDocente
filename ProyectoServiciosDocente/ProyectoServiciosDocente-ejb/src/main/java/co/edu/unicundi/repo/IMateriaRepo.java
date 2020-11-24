/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.repo;

import co.edu.unicundi.entity.Materia;
import java.util.List;

/**
 *
 * @author YEISON
 */
public interface IMateriaRepo {

    public void registrar(Materia materia);

    public List<Materia> listar();

    public void editar(Materia materia);

    public void eliminar(Materia materia);

    public Materia obtenerPorId(Integer id);
    
    public Materia obtenerPorNombre(String nombre);

    public List<Materia> listarNoAsociadas(Integer idDocente);

}
