/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pojos;

import org.bson.types.ObjectId;

/**
 *
 * @author Gael
 */
/**
 * Clase que representa a un Cliente del restaurante.
 * Se almacena en la base de datos MongoDB como un documento.
 */
public class Cliente {

    /**
     * Identificador único del cliente (generado por MongoDB).
     */
    private ObjectId id;

    /**
     * Nombre completo del cliente.
     */
    private String nombre;

    /**
     * Correo electrónico del cliente.
     */
    private String correo;

    /**
     * Teléfono de contacto del cliente.
     */
    private String telefono;

    /**
     * Constructor vacío
     */
    public Cliente() {
    }

    /**
     * Constructor que inicializa todos los campos del cliente.
     * 
     * @param id Identificador único del cliente.
     * @param nombre Nombre completo.
     * @param correo Correo electrónico.
     * @param telefono Teléfono de contacto.
     */
    public Cliente(ObjectId id, String nombre, String correo, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}

