/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.BD;

import co.edu.unicundi.docentePOJO.DocentePOJO;
import com.google.gson.Gson;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author YEISON
 */
public class DAODocente {

    /**
     * listar todos los artistas
     *
     * @return
     */
    public ArrayList<DocentePOJO> listar() {
        String estado;
        ArrayList<DocentePOJO> docentes = new ArrayList();
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "SELECT * FROM docente.tbl_docente;";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {

                    ObjectMapper mapper = new ObjectMapper();
                    List<String> materias = new ArrayList();

                    try {
                        materias = mapper.readValue(resultado.getString("materias"), List.class);

                        docentes.add(new DocentePOJO(resultado.getInt("id"),
                                resultado.getString("cedula"),
                                materias,
                                resultado.getString("nombre"),
                                resultado.getString("apellido")));
                    } catch (IOException ex) {
                        Logger.getLogger(DAODocente.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return docentes;
    }

    public void editar(DocentePOJO docente) {
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String json = new Gson().toJson(docente.getMaterias());
                String query = "UPDATE docente.tbl_docente SET cedula='" + docente.getCedula()
                        + "', materias= '" + json
                        + "', nombre='" + docente.getNombre()
                        + "', apellido='" + docente.getApellido()
                        + "' WHERE  id='" + docente.getId() + "';";
                PreparedStatement stmt = conexion.prepareStatement(query);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
