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
     * Registrar docente en BD
     *
     * @param docente
     */
    public void registrar(DocentePOJO docente) {
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String materias = new Gson().toJson(docente.getMaterias());
                String query = "INSERT INTO docente.tbl_docente(cedula, materias, nombre, apellido, correo) VALUES ('"
                        + docente.getCedula() + "','"
                        + materias + "','"
                        + docente.getNombre() + "','"
                        + docente.getApellido() + "','"
                        + docente.getCorreo() + "');";
                PreparedStatement stmt = conexion.prepareStatement(query);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * listar todos los docentes
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
                                resultado.getString("apellido"),
                                resultado.getString("correo")));
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

    /**
     * Obtener docente de la BD filtrado por cedula
     *
     * @param cedula
     * @return
     */
    public DocentePOJO obtenerPorCedula(String cedula) {
        DocentePOJO docente = new DocentePOJO();
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "SELECT * FROM docente.tbl_docente WHERE tbl_docente.cedula = '" + cedula + "';";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {

                    ObjectMapper mapper = new ObjectMapper();
                    List<String> materias = new ArrayList();
                    try {
                        materias = mapper.readValue(resultado.getString("materias"), List.class);
                        docente = new DocentePOJO(
                                resultado.getInt("id"),
                                resultado.getString("cedula"),
                                materias,
                                resultado.getString("nombre"),
                                resultado.getString("apellido"),
                                resultado.getString("correo"));
                    } catch (IOException ex) {
                        Logger.getLogger(DAODocente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return docente;
    }

    /**
     * Edita los datos de un docente en BD
     *
     * @param docente
     */
    public void editar(DocentePOJO docente) {
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String json = new Gson().toJson(docente.getMaterias());
                String query = "UPDATE docente.tbl_docente SET cedula='" + docente.getCedula()
                        + "', materias= '" + json
                        + "', nombre='" + docente.getNombre()
                        + "', apellido='" + docente.getApellido()
                        + "', correo='" + docente.getCorreo()
                        + "' WHERE  id='" + docente.getId() + "';";
                PreparedStatement stmt = conexion.prepareStatement(query);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Elimina un docente en la BD
     *
     * @param id
     */
    public void eliminar(int id) {
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {

                String query = "DELETE FROM docente.tbl_docente WHERE id='" + id + "';";
                PreparedStatement stmt = conexion.prepareStatement(query);
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Obtener docente de la BD filtrado por id
     *
     * @param id
     * @return
     */
    public DocentePOJO obtenerPorId(int id) {
        DocentePOJO docente = new DocentePOJO();
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "SELECT * FROM docente.tbl_docente WHERE tbl_docente.id = '" + id + "';";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {

                    ObjectMapper mapper = new ObjectMapper();
                    List<String> materias = new ArrayList();
                    try {
                        materias = mapper.readValue(resultado.getString("materias"), List.class);
                        docente = new DocentePOJO(
                                resultado.getInt("id"),
                                resultado.getString("cedula"),
                                materias,
                                resultado.getString("nombre"),
                                resultado.getString("apellido"),
                                resultado.getString("correo"));
                    } catch (IOException ex) {
                        Logger.getLogger(DAODocente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return docente;
    }

    /**
     * Obtener docente de la BD filtrado por cedula y correo para validar
     *
     * @param cedula
     * @return
     */
    public List<DocentePOJO> obtenerPorCedulaYCorreo(String cedula, String correo) {
        List<DocentePOJO> docentes = new ArrayList();
        Connection conexion = new BDConector().open();
        if (conexion != null) {
            try {
                String query = "SELECT * FROM docente.tbl_docente WHERE tbl_docente.cedula = '" + cedula + "' "
                        + "OR tbl_docente.correo = '" + correo + "';";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {

                    ObjectMapper mapper = new ObjectMapper();
                    List<String> materias = new ArrayList();
                    try {
                        materias = mapper.readValue(resultado.getString("materias"), List.class);
                        docentes.add(new DocentePOJO(
                                resultado.getInt("id"),
                                resultado.getString("cedula"),
                                materias,
                                resultado.getString("nombre"),
                                resultado.getString("apellido"),
                                resultado.getString("correo")));
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
}
