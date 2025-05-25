/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;


import Pojos.Mesero;
import dtos.MeseroDTO;
import org.bson.types.ObjectId;

/**
 *
 * @author Gael
 */
/**
 * Mapper para convertir entre entidad Mesero y su DTO.
 */
public class MeseroMapper {

    /**
     * Convierte un MeseroDTO a entidad Mesero.
     *
     * @param dto DTO con los datos del mesero.
     * @return Entidad Mesero para persistencia.
     */
    public static Mesero toEntity(MeseroDTO dto) {
    if (dto == null) return null;

    Mesero mesero = new Mesero();

    if (dto.getId() != null && !dto.getId().isBlank()) {
        mesero.setId(new ObjectId(dto.getId()));
    }
    
    mesero.setNombre(dto.getNombre());
    mesero.setTelefono(dto.getTelefono());
    mesero.setFechaNacimiento(dto.getFechaNacimiento());
    mesero.setDireccion(dto.getDireccion());
    mesero.setEstado(dto.isEstado());
    return mesero;
}

public static MeseroDTO toDTO(Mesero mesero) {
    if (mesero == null) return null;

    MeseroDTO dto = new MeseroDTO();

    if (mesero.getId() != null) {
        dto.setId(mesero.getId().toHexString());
    }

    dto.setNombre(mesero.getNombre());
    dto.setTelefono(mesero.getTelefono());
    dto.setFechaNacimiento(mesero.getFechaNacimiento());
    dto.setDireccion(mesero.getDireccion());
    dto.setEstado(mesero.isEstado());
    return dto;
}
}


