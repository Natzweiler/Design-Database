/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;


import Pojos.Cliente;
import dtos.ClienteDTO;

/**
 *
 * @author Gael
 */
/**
 * Mapper para convertir entre entidad Cliente y su DTO.
 */
public class ClienteMapper {

    /**
     * Convierte una entidad Cliente a ClienteDTO.
     *
     * @param cliente Entidad Cliente.
     * @return DTO con datos del cliente.
     */
    public static ClienteDTO toDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setNombre(cliente.getNombre());
        dto.setCorreo(cliente.getCorreo());
        dto.setTelefono(cliente.getTelefono());
        return dto;
    }

    /**
     * Convierte un ClienteDTO a entidad Cliente.
     *
     * @param dto DTO con datos del cliente.
     * @return Entidad lista para persistencia.
     */
    public static Cliente toEntity(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setCorreo(dto.getCorreo());
        cliente.setTelefono(dto.getTelefono());
        return cliente;
    }
}


