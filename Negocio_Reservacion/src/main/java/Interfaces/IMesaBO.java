/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import dtos.MesaDTO;
import java.util.List;
import negocio.exception.NegocioException;

/**
 *
 * @author Gael
 */
/**
 * Interfaz para las operaciones de negocio relacionadas con Mesa.
 */
public interface IMesaBO {

    /**
     * Obtiene una mesa por su número.
     * 
     * @param numeroMesa Número de la mesa.
     * @return DTO con la información de la mesa.
     * @throws NegocioException si ocurre algún error en la operación.
     */
    MesaDTO obtenerMesaPorNumeroMesa(Integer numeroMesa) throws NegocioException;

    /**
     * Genera las mesas iniciales del sistema (por ejemplo, inserta las mesas base).
     * 
     * @return Número de mesas generadas.
     * @throws NegocioException si ocurre algún error al generar las mesas.
     */
    int generarMesasIniciales() throws NegocioException;

    /**
     * Obtiene todas las mesas registradas en el sistema.
     * 
     * @return Lista de DTOs de mesas.
     * @throws NegocioException si ocurre algún error al obtener las mesas.
     */
    List<MesaDTO> obtenerTodasLasMesas() throws NegocioException;

    /**
     * Actualiza el estado de disponibilidad de una mesa.
     * 
     * @param numeroMesa Número de la mesa a actualizar.
     * @param disponible Estado de disponibilidad (true = disponible, false = ocupada).
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws NegocioException si ocurre algún error durante la actualización.
     */
    boolean actualizarEstadoMesa(int numeroMesa, boolean disponible) throws NegocioException;
}

