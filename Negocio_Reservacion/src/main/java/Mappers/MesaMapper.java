/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import Pojos.Mesa;
import dtos.MesaDTO;

/**
 *
 * @author Gael
 */
/**
 * Mapper para convertir entre entidad Mesa y su DTO.
 */
public class MesaMapper {

    /**
     * Convierte una entidad Mesa a un MesaDTO.
     *
     * @param mesa Entidad Mesa desde base de datos.
     * @return DTO con los datos de la mesa.
     */
    public static MesaDTO toDTO(Mesa mesa) {
        MesaDTO dto = new MesaDTO();
        dto.setNumeroMesa(mesa.getNumeroMesa());
        dto.setCapacidadMesa(mesa.getCapacidadMesa());
        dto.setDisponible(mesa.isDisponible());
        dto.setEstadoMesa(mesa.getEstadoMesa()); // si lo usas
        return dto;
    }

    /**
     * Convierte un DTO MesaDTO a una entidad Mesa.
     *
     * @param dto DTO con información de la mesa.
     * @return Entidad lista para persistencia.
     */
    public static Mesa toEntity(MesaDTO dto) {
        Mesa mesa = new Mesa();
        mesa.setNumeroMesa(dto.getNumeroMesa());
        mesa.setCapacidadMesa(dto.getCapacidadMesa());
        mesa.setDisponible(dto.isDisponible());
        mesa.setEstadoMesa(dto.getEstadoMesa());
        return mesa;
    }
}
