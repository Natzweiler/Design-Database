/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;


import Interfaces.IReservacionBO;
import Mappers.ReservacionMapper;
import Persistencia.MesaDAO;
import Persistencia.MeseroDAO;
import Persistencia.ReservacionDAO;
import Pojos.Mesa;
import Pojos.Mesero;
import Pojos.Reservacion;
import dtos.ReservacionDTO;
import exception.PersistenciaException;
import interfaces.IMeseroDAO;
import interfaces.IReservacionDAO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import negocio.exception.NegocioException;

/**
 *
 * @author Gael
 */
/**
 * Clase de negocio para la gestión de reservaciones.
 * Implementa la interfaz IReservacionBO.
 */
public class ReservacionBO implements IReservacionBO {

    private IReservacionDAO reservacionDAO; 
    private IMeseroDAO meseroDAO;
        /**
     * Constructor que recibe un DAO de reservaciones.
     * Inicializa también el DAO de meseros.
     * 
     * @param reservacionDAO DAO para acceso a datos de reservaciones.
     */
    public ReservacionBO(IReservacionDAO reservacionDAO) {
       this.reservacionDAO = new ReservacionDAO();
       this.meseroDAO = MeseroDAO.getInstanceDAO();
    }
    /**
     * Registra una nueva reservación con validaciones básicas.
     * 
     * @param dto DTO con datos de la reservación a registrar.
     * @return DTO de la reservación registrada.
     * @throws NegocioException si los datos son inválidos o hay error en persistencia.
     */
    public ReservacionDTO registrarReservacion(ReservacionDTO dto) throws NegocioException {
    try {
        if (dto.getCliente().getNombre() == null || dto.getCliente().getNombre().isEmpty()) {
            throw new NegocioException("El nombre del cliente no puede estar vacío.");
        }
        if (dto.getCliente().getCorreo() == null || dto.getCliente().getCorreo().isEmpty()) {
            throw new NegocioException("El correo del cliente no puede estar vacío.");
        }
        if (dto.getCliente().getTelefono() == null || dto.getCliente().getTelefono().isEmpty()) {
            throw new NegocioException("El teléfono del cliente no puede estar vacío.");
        }

        if (!dto.getMesa().isDisponible()) {
            throw new NegocioException("La mesa ya está ocupada");
        }

        // conversion a entidad
        Reservacion reservacion = ReservacionMapper.toEntity(dto);

        
        Mesero meseroRecuperado = meseroDAO.obtenerMeseroPorIdString(dto.getMesero().getId());
        Mesa mesaRecuperada = MesaDAO.getInstanceDAO().obtenerMesaPorNumeroMesa(dto.getMesa().getNumeroMesa());

        // asignarlas
        reservacion.setMesero(meseroRecuperado);
        reservacion.setMesa(mesaRecuperada);

        // persistir la reservación
        Reservacion nuevaReservacion = reservacionDAO.registrarReservacion(reservacion);

        return ReservacionMapper.toDTO(nuevaReservacion);

    } catch (NegocioException ne) {
        throw ne;
    } catch (Exception e) {
        throw new NegocioException("Ocurrió un error al registrar la reservación: " + e.getMessage(), e);
    }
}
        /**
     * Lista todas las reservaciones.
     * 
     * @return Lista de DTOs de reservaciones.
     * @throws NegocioException si ocurre un error en persistencia.
     */
    @Override
    public List<ReservacionDTO> listarReservaciones() throws NegocioException {
        try {
            return reservacionDAO.listarReservaciones()
                                  .stream()
                                  .map(ReservacionMapper::toDTO)
                                  .collect(Collectors.toList());
        } catch (PersistenciaException pe) {
            throw new NegocioException("Error al listar reservaciones: " + pe.getMessage(), pe);
        }
    }
     /**
     * Cambia el estado de las mesas.
     * 
     * @return boolean.
     * @throws NegocioException si ocurre un error en persistencia.
     */
    public boolean estadoMesaDisponible(Integer numeroMesa, LocalDate fecha, LocalTime hora) throws NegocioException {
    try {
        // Recuperar la mesa desde la base de datos
        MesaDAO mesaDAO = MesaDAO.getInstanceDAO();
        Mesa mesa = mesaDAO.obtenerMesaPorNumeroMesa(numeroMesa);
        if (mesa == null) {
            throw new NegocioException("La mesa con número " + numeroMesa + " no existe.");
        }

        
        return reservacionDAO.estadoMesaDisponible(numeroMesa, fecha, hora);
        
    } catch (PersistenciaException e) {
        throw new NegocioException("Error al verificar disponibilidad de la mesa: " + e.getMessage(), e);
    }
}

}
