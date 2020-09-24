/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.exception;

/**
 * Excepción que se dispara cuando el cliente no envia el parámetro id
 *
 * @author Camilo Sanabria
 * @version 1.0.0
 */
public class IdRequiredException extends RuntimeException {

    /**
     * Constructor de clase
     *
     * @param message
     */
    public IdRequiredException(String message) {
        super(message);
    }

}
