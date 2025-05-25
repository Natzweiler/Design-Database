package Mappers;


import Pojos.Reservacion;
import dtos.ReservacionDTO;

/**
 * Mapper para convertir entre entidades Reservacion y DTOs correspondientes.
 */
public class ReservacionMapper {

    /**
     * Convierte una entidad Reservacion a un ReservacionDTO (sin ID).
     *
     * @param reservacion Entidad Reservacion desde la base de datos.
     * @return DTO para uso en capa de negocio o presentación.
     */
    public static ReservacionDTO toDTO(Reservacion reservacion) {
        ReservacionDTO dto = new ReservacionDTO();
        dto.setMesa(MesaMapper.toDTO(reservacion.getMesa()));
        dto.setCliente(ClienteMapper.toDTO(reservacion.getCliente()));
        dto.setFecha(reservacion.getFecha());
        dto.setHora(reservacion.getHora());
        dto.setMesero(MeseroMapper.toDTO(reservacion.getMesero()));
        return dto;
    }

    /**
     * Convierte un DTO ReservacionDTO a una entidad Reservacion (sin ID).
     *
     * @param dto DTO con los datos de la reservación.
     * @return Entidad lista para guardarse en base de datos.
     */
    public static Reservacion toEntity(ReservacionDTO dto) {
        Reservacion reservacion = new Reservacion();
        reservacion.setMesa(MesaMapper.toEntity(dto.getMesa()));
        reservacion.setCliente(ClienteMapper.toEntity(dto.getCliente()));
        reservacion.setFecha(dto.getFecha());
        reservacion.setHora(dto.getHora());
        reservacion.setMesero(MeseroMapper.toEntity(dto.getMesero()));
        return reservacion;
    }

}

