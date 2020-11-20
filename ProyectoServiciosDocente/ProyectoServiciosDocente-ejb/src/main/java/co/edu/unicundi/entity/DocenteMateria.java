/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author YEISON
 */
@Entity
@Table(name = "docentes.docente_materia")
public class DocenteMateria implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private DocenteMateriaPK docenteMateriaId;

    @NotNull(message = "Objeto docente requerido")
    @ManyToOne
    @MapsId("idDocente")
    private Docente docente;

    @NotNull(message = "Objeto materia requerido")
    @ManyToOne
    @MapsId("idMateria")
    private Materia materia;

    public DocenteMateriaPK getDocenteMateriaId() {
        return docenteMateriaId;
    }

    public void setDocenteMateriaId(DocenteMateriaPK docenteMateriaId) {
        this.docenteMateriaId = docenteMateriaId;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

}
