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
 * Clase que representa una Mesa dentro del restaurante.
 * Cada mesa puede estar disponible u ocupada en una fecha y hora.
 */
public class Mesa {

    /**
     * Identificador único de la mesa (generado por MongoDB).
     */
    private ObjectId id;

    /**
     * Número de la mesa (único en el restaurante).
     */
    private Integer numeroMesa;

    /**
     * Capacidad máxima de personas que pueden sentarse en esta mesa.
     */
    private int capacidadMesa;

    /**
     * Estado de disponibilidad en general (se puede usar para control manual).
     */
    private boolean disponible;

    /**
     * Estado visual de la mesa (por ejemplo, 0 = libre, 1 = ocupada, etc.).
     */
    private int estadoMesa;

    /**
     * Constructor por defecto necesario para serialización y deserialización.
     */
    public Mesa() {
    }

    /**
     * Constructor que inicializa todos los campos de la mesa.
     * 
     * @param id Identificador único.
     * @param numeroMesa Número identificador de la mesa.
     * @param capacidadMesa Cantidad de personas que caben.
     * @param disponible Indica si está disponible.
     * @param estadoMesa Estado visual o lógico de la mesa.
     */
    public Mesa(ObjectId id, Integer numeroMesa, int capacidadMesa, boolean disponible, int estadoMesa) {
        this.id = id;
        this.numeroMesa = numeroMesa;
        this.capacidadMesa = capacidadMesa;
        this.disponible = disponible;
        this.estadoMesa = estadoMesa;
    }

    

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public int getCapacidadMesa() {
        return capacidadMesa;
    }

    public void setCapacidadMesa(int capacidadMesa) {
        this.capacidadMesa = capacidadMesa;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getEstadoMesa() {
        return estadoMesa;
    }

    public void setEstadoMesa(int estadoMesa) {
        this.estadoMesa = estadoMesa;
    }
}
