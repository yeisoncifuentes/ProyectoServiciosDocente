/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.BD;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que conecta al sistema con la Base de Datos
 * @author YEISON
 * @version 1.0.0
 */
public class BDConector implements Serializable {

    /**
     * Manejador de JDBC
     */
    private final String JDBC_DRIVER = "org.postgresql.Driver";
    
    /**
     * Direccion a la que se conecta BD
     */
    private final String DB_URL = "jdbc:postgresql://localhost:5432/docente";
    
    /**
     * Usuario de postgreSQL
     */
    private String user;
    
    /**
     * Contraseña de postgreSQL
     */
    private String pass;
    
    /**
     * Variable de conexion
     */
    private Connection conexion;

    /**
     * Constructor de clase
     */
    public BDConector() {
        this.user = "postgres";
        this.pass = "root";
    }

    /**
     * Abrir conexion
     * @return La conexion realizada
     */
    public Connection open() {
        try {
            Class.forName(JDBC_DRIVER);
            try {
                conexion = DriverManager.getConnection(DB_URL, user, pass);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("No se pudo conectar a la BD");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver no encontrado");
        }
        return conexion;
    }

    /**
     * Cierra la conexion
     */
    public void close() {
        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println("No se pudo cerrar la conexion");
        }
    }
}