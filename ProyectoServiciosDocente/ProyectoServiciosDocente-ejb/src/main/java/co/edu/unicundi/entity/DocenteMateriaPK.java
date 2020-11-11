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

/**
 *
 * @author YEISON
 */
@Embeddable
public class DocenteMateriaPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_docente")
    private Integer idDocente;

    @Column(name = "id_materia")
    private Integer idMateria;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.idDocente);
        hash = 31 * hash + Objects.hashCode(this.idMateria);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DocenteMateriaPK other = (DocenteMateriaPK) obj;
        if (!Objects.equals(this.idDocente, other.idDocente)) {
            return false;
        }
        if (!Objects.equals(this.idMateria, other.idMateria)) {
            return false;
        }
        return true;
    }
    
    


}
