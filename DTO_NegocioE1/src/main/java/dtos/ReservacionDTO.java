/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Gael
 */
import java.time.LocalDate;
import java.time.LocalTime;

public class ReservacionDTO {
    
    private String id;
    
    private MesaDTO mesa;

    private ClienteDTO cliente;

    private LocalDate fecha;

    private MeseroDTO mesero;

    private LocalTime hora;

   

    public ReservacionDTO() {
    }

    public ReservacionDTO(String id, MesaDTO mesa, ClienteDTO cliente, LocalDate fecha, MeseroDTO mesero, LocalTime hora) {
        this.id = id;
        this.mesa = mesa;
        this.cliente = cliente;
        this.fecha = fecha;
        this.mesero = mesero;
        this.hora = hora;
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MesaDTO getMesa() {
        return mesa;
    }

    public void setMesa(MesaDTO mesa) {
        this.mesa = mesa;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public MeseroDTO getMesero() {
        return mesero;
    }

    public void setMesero(MeseroDTO mesero) {
        this.mesero = mesero;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "ReservacionDTO{" + "id=" + id + ", mesa=" + mesa + ", cliente=" + cliente + ", fecha=" + fecha + ", mesero=" + mesero + ", hora=" + hora + '}';
    }

    
    
}
 