
package Interfaces;


import dtos.ReservacionDTO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import negocio.exception.NegocioException;

/**
 *
 * @author Gael
 */
public interface IReservacionBO {

    /**
     * Registra una nueva reservación.
     * 
     * @param dto DTO con la información de la reservación a registrar.
     * @return DTO con la reservación registrada.
     * @throws NegocioException si ocurre algún error al registrar.
     */
    ReservacionDTO registrarReservacion(ReservacionDTO dto) throws NegocioException;

    /**
     * Obtiene la lista de todas las reservaciones registradas.
     * 
     * @return Lista de DTOs de reservaciones.
     * @throws NegocioException si ocurre algún error al obtener la lista.
     */
    List<ReservacionDTO> listarReservaciones() throws NegocioException;

    /**
     * Verifica si el estado de una mesa está disponible en una fecha y hora determinada.
     * 
     * @param numeroMesa Número de la mesa.
     * @param fecha Fecha de la reservación.
     * @param hora Hora de la reservación.
     * @return true si la mesa está disponible, false si está ocupada.
     * @throws NegocioException si ocurre algún error en la verificación.
     */
    boolean estadoMesaDisponible(Integer numeroMesa, LocalDate fecha, LocalTime hora) throws NegocioException;
}
