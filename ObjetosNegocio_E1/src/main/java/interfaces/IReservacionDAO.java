/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;


import Pojos.Reservacion;
import exception.PersistenciaException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author Gael
 */
/**
 * Interfaz DAO para operaciones relacionadas con la entidad {@link Reservacion}.
 * Proporciona métodos para registrar, listar y verificar disponibilidad de reservaciones.
 */
public interface IReservacionDAO {

    /**
     * Registra una nueva reservación en la base de datos.
     * @param reservacion Reservación a registrar.
     * @return La reservación registrada.
     * @throws PersistenciaException Si ocurre un error durante la inserción.
     */
    public Reservacion registrarReservacion(Reservacion reservacion) throws PersistenciaException;

    /**
     * Obtiene todas las reservaciones almacenadas en la base de datos.
     * @return Lista de reservaciones.
     * @throws PersistenciaException Si ocurre un error al realizar la consulta.
     */
    public List<Reservacion> listarReservaciones() throws PersistenciaException;

    /**
     * Verifica si una mesa está disponible para una fecha y hora específicas.
     * @param numeroMesa Número de la mesa a verificar.
     * @param fecha Fecha deseada para la reservación.
     * @param hora Hora deseada para la reservación.
     * @return {@code true} si la mesa está disponible, {@code false} si ya está reservada.
     * @throws PersistenciaException Si ocurre un error al acceder a la base de datos.
     */
    public boolean estadoMesaDisponible(Integer numeroMesa, LocalDate fecha, LocalTime hora) throws PersistenciaException;
}
