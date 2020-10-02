/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.exception;

/**
 * Excepción que se dispara cuando la base de datos no responde
 *
 * @author Camilo Sanabria
 * @version 1.0.0
 */
public class NoResponseBDException extends Exception {

    /**
     * Constructor de clase
     *
     * @param message
     */
    public NoResponseBDException(String message) {
        super(message);
    }
    
}
