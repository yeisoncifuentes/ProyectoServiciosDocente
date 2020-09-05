/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.logica;

import co.edu.unicundi.BD.DAODocente;
import co.edu.unicundi.docentePOJO.DocentePOJO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Clase que permite hacer la logica de los servicios
 * @author YEISON
 */
@Stateless
public class LogicaDocente {

    /**
     * Listar todos los docentes
     * @return 
     */
    public List<DocentePOJO> listar() {
        return new DAODocente().listar();
    }

    /**
     * Editar los datos del docente
     * @param docente 
     */
    public void editar(DocentePOJO docente) {
        new DAODocente().editar(docente);
    }

    /**
     * Obtener los docentes de la materia ingresada
     * @param materia
     * @return 
     */
    public List<DocentePOJO> obtenerDocentesMateria(String materia) {
        List<DocentePOJO> todosDocentes = new DAODocente().listar();

        List<DocentePOJO> docentesMateria = new ArrayList();

        for (DocentePOJO docente : todosDocentes) {
            for (String materiaDocente : docente.getMaterias()) {
                
                if (materiaDocente.equals(materia)) {
                    System.out.println(materiaDocente);
                    docentesMateria.add(docente);
                }
            }
        }
        return docentesMateria;
    }
}
