/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author Gael
 */
public class MesaDTO {
    
    private int numeroMesa;
    
    private int capacidadMesa;
    
    private boolean disponible;

    private int estadoMesa;
    
    public MesaDTO() {
    this.estadoMesa = 1;
    }
    
   
    
    public MesaDTO(int numeroMesa, int capacidadMesa, boolean disponible) {
        this();
        this.numeroMesa = numeroMesa;
        this.capacidadMesa = capacidadMesa;
        this.disponible = disponible;
        
    }

  

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public int getCapacidadMesa() {
        return capacidadMesa;
    }

    public void setCapacidadMesa(int capacidadMesa) {
        this.capacidadMesa = capacidadMesa;
    }

    public int getEstadoMesa() {
        return estadoMesa;
    }

    public void setEstadoMesa(int EstadoMesa) {
        this.estadoMesa = EstadoMesa;
    }
    
    

    @Override
public String toString() {
    return "MesaDTO{" +
           "numeroMesa=" + numeroMesa +
           ", capacidadMesa=" + capacidadMesa +
           ", disponible=" + disponible +
           ", estadoMesa=" + estadoMesa +
           '}';
}

    
    
}
