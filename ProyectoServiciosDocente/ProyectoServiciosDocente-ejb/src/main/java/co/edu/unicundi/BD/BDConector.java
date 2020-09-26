/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.BD;

import co.edu.unicundi.exception.ListNoContentException;
import co.edu.unicundi.exception.NoResponseBDException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que conecta al sistema con la Base de Datos
 *
 * @author Yeison Cifuentes
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
    private final String DB_URL = "jdbc:postgresql://localhost:5432/Docentes";

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
        this.pass = "XVV254";
    }

    /**
     * Abrir conexion
     *
     * @return La conexion realizada
     */
    public Connection open() {
        try {
            Class.forName(JDBC_DRIVER);
            try {
                conexion = DriverManager.getConnection(DB_URL, user, pass);
            } catch (SQLException e) {
                throw new NoResponseBDException("La Base de datos no responde");
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
