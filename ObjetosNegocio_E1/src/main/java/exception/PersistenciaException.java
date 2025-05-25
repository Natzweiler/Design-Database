/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author Gael
 */
/**
 * Excepción personalizada para representar errores en la capa de persistencia.
 * Se lanza cuando ocurre un error relacionado con la base de datos.
 */
public class PersistenciaException extends Exception {

    /**
     * Crea una nueva instancia de {@code PersistenciaException} con un mensaje.
     * @param message Mensaje descriptivo del error.
     */
    public PersistenciaException(String message) {
        super(message);
    }

    /**
     * Crea una nueva instancia de {@code PersistenciaException} con un mensaje y la causa original.
     * @param message Mensaje descriptivo del error.
     * @param cause Causa original del error (excepción anidada).
     */
    public PersistenciaException(String message, Throwable cause) {
        super(message, cause);
    }
}
