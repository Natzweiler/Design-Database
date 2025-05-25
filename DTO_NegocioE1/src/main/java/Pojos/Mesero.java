/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pojos;

import java.time.LocalDate;
import org.bson.types.ObjectId;

/**
 *
 * @author Gael
 */
/**
 * Clase que representa un Mesero del restaurante.
 * Contiene información personal y de estado para su gestión.
 */
public class Mesero {

    /**
     * Identificador único del mesero (generado por MongoDB).
     */
    private ObjectId id;

    /**
     * Nombre completo del mesero.
     */
    private String nombre;

    /**
     * Teléfono de contacto del mesero.
     */
    private String telefono;

    /**
     * Dirección de residencia del mesero.
     */
    private String direccion;

    /**
     * Fecha de nacimiento del mesero.
     */
    private LocalDate fechaNacimiento;

    /**
     * Estado del mesero (true = activo, false = inactivo).
     */
    private boolean estado;

    /**
     * Constructor por defecto necesario para serialización y deserialización.
     */
    public Mesero() {
    }

    /**
     * Constructor que inicializa todos los campos del mesero.
     * 
     * @param id Identificador único.
     * @param nombre Nombre completo.
     * @param telefono Teléfono.
     * @param direccion Dirección.
     * @param fechaNacimiento Fecha de nacimiento.
     * @param estado Estado de actividad.
     */
    public Mesero(ObjectId id, String nombre, String telefono, String direccion, LocalDate fechaNacimiento, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
