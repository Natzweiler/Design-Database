/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pojos;

import java.time.LocalDate;
import java.time.LocalTime;
import org.bson.types.ObjectId;

/**
 *
 * @author Gael
 */
/**
 * Clase que representa una Reservación realizada por un cliente.
 * Contiene la relación con la mesa, cliente, mesero, fecha y hora.
 */
public class Reservacion {

    /**
     * Identificador único de la reservación.
     */
    private ObjectId id;

    /**
     * Mesa asignada para la reservación.
     */
    private Mesa mesa;

    /**
     * Cliente que realizó la reservación.
     */
    private Cliente cliente;

    /**
     * Mesero asignado a la reservación.
     */
    private Mesero mesero;

    /**
     * Fecha en la que se realizará la reservación.
     */
    private LocalDate fecha;

    /**
     * Hora de la reservación.
     */
    private LocalTime hora;

    /**
     * Constructor por defecto necesario para serialización y deserialización.
     */
    public Reservacion() {
    }

    /**
     * Constructor que inicializa todos los campos de la reservación.
     * 
     * @param id Identificador único.
     * @param mesa Mesa asignada.
     * @param cliente Cliente que reservó.
     * @param mesero Mesero asignado.
     * @param fecha Fecha de la reservación.
     * @param hora Hora de la reservación.
     */
    public Reservacion(ObjectId id, Mesa mesa, Cliente cliente, Mesero mesero, LocalDate fecha, LocalTime hora) {
        this.id = id;
        this.mesa = mesa;
        this.cliente = cliente;
        this.mesero = mesero;
        this.fecha = fecha;
        this.hora = hora;
    }

    

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Mesero getMesero() {
        return mesero;
    }

    public void setMesero(Mesero mesero) {
        this.mesero = mesero;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    /**
     * Establece el ID como un ObjectId a partir de su representación en cadena.
     * 
     * @param id Cadena con formato hexadecimal de MongoDB.
     */
    public void setObjectString(String id) {
        if (id != null && !id.isBlank()) {
            this.id = new ObjectId(id);
        }
    }

    /**
     * Devuelve el ID de la reservación en formato hexadecimal (String).
     * 
     * @return Cadena con el ObjectId en formato hexadecimal.
     */
    public String getObjectString() {
        return id != null ? id.toHexString() : null;
    }
}

