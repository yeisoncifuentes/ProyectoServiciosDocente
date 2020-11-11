/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.POJO;

import java.io.Serializable;

/**
 *
 * @author YEISON
 */
public class DocenteMateriaPOJO implements Serializable {

    private DocentePOJO docente;

    private MateriaPOJO materia;

    public DocentePOJO getDocente() {
        return docente;
    }

    public void setDocente(DocentePOJO docente) {
        this.docente = docente;
    }

    public MateriaPOJO getMateria() {
        return materia;
    }

    public void setMateria(MateriaPOJO materia) {
        this.materia = materia;
    }

}
