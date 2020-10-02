/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.exception;

/**
 * Excepción que se dispara cuando se va a crear o editar un objeto ya está
 * registrado en base de datos
 *
 * @author Camilo Sanabria
 * @version 1.0.0
 */
public class RegisteredObjectException extends Exception {

    /**
     * Constructor de clase
     *
     * @param message
     */
    public RegisteredObjectException(String message) {
        super(message);
    }

}
