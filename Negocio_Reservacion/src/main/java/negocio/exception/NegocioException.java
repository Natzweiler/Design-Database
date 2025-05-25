/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.exception;

/**
 *
 * @author Gael
 */
/**
 * Excepción personalizada para errores en la capa de negocio.
 */
public class NegocioException extends Exception {
        /**
     * Constructor con mensaje de error.
     * 
     * @param message mensaje de error descriptivo.
     */
    public NegocioException(String message) {
        super(message);
    }
        /**
     * Constructor con mensaje y causa original.
     * 
     * @param message mensaje de error descriptivo.
     * @param cause causa original de la excepción.
     */
    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }
    
}

