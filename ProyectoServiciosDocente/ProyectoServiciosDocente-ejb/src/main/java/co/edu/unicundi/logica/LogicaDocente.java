/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.logica;

import co.edu.unicundi.BD.DAODocente;
import co.edu.unicundi.docentePOJO.DocentePOJO;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author YEISON
 */

public class LogicaDocente {

    public List<DocentePOJO> listar() {
        return new DAODocente().listar();
    }
}
