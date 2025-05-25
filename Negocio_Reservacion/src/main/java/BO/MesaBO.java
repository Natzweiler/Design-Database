/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;


import Interfaces.IMesaBO;
import Mappers.MesaMapper;
import Persistencia.MesaDAO;
import Pojos.Mesa;
import dtos.MesaDTO;
import exception.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import negocio.exception.NegocioException;

/**
 *
 * @author Gael
 */
/**
 * Clase de negocio para la gestión de mesas.
 * Implementa la interfaz IMesaBO.
 */
public class MesaBO implements IMesaBO {

    private final MesaDAO mesaDAO;

    /**
     * Constructor que inicializa el DAO de mesas.
     */
    public MesaBO() {
        this.mesaDAO = MesaDAO.getInstanceDAO();
    }

    /**
     * Obtiene una mesa por su número.
     * 
     * @param idMesa Número identificador de la mesa.
     * @return DTO de la mesa encontrada.
     * @throws NegocioException si ocurre un error en persistencia.
     */
    public MesaDTO obtenerMesaPorNumeroMesa(Integer idMesa) throws NegocioException {
        try {
            Mesa mesa = mesaDAO.obtenerMesaPorNumeroMesa(idMesa);
            return MesaMapper.toDTO(mesa);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener la mesa: " + e.getMessage(), e);
        }
    }

    /**
     * Genera un conjunto inicial de 7 mesas con capacidad 4 y las registra en BD.
     * 
     * @return Cantidad de mesas generadas.
     * @throws NegocioException si ocurre error en persistencia.
     */
    public int generarMesasIniciales() throws NegocioException {
        try {
            int contador = 0;
            for (int i = 1; i <= 7; i++) {
                Mesa mesa = new Mesa();
                mesa.setNumeroMesa(i);
                mesa.setCapacidadMesa(4);
                mesa.setDisponible(true);
                mesa.setEstadoMesa(contador);
                mesaDAO.registrarMesa(mesa);
                contador++;
            }
            return contador;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al generar mesas iniciales", e);
        }
    }

    /**
     * Obtiene todas las mesas registradas.
     * 
     * @return Lista de DTOs de mesas.
     * @throws NegocioException si ocurre error en persistencia.
     */
    public List<MesaDTO> obtenerTodasLasMesas() throws NegocioException {
        try {
            List<Mesa> mesas = mesaDAO.obtenerTodasLasMesas();
            List<MesaDTO> dtoList = new ArrayList<>();
            for (Mesa mesa : mesas) {
                dtoList.add(MesaMapper.toDTO(mesa));
            }
            return dtoList;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener las mesas", e);
        }
    }

    /**
     * Actualiza el estado de disponibilidad de una mesa.
     * 
     * @param numeroMesa Número de la mesa a actualizar.
     * @param disponible Nuevo estado de disponibilidad.
     * @return true si se actualizó correctamente.
     * @throws NegocioException si ocurre error en persistencia.
     */
    public boolean actualizarEstadoMesa(int numeroMesa, boolean disponible) throws NegocioException {
        try {
            return mesaDAO.actualizarDisponibilidadMesa(numeroMesa, disponible);
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo actualizar el estado de la mesa", e);
        }
    }
}

