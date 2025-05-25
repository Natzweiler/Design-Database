/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;


import Pojos.Mesero;
import dtos.MeseroDTO;
import java.util.List;
import negocio.exception.NegocioException;

/**
 *
 * @author Gael
 */
/**
 * Interfaz para las operaciones de negocio relacionadas con Mesero.
 */
public interface IMeseroBO {

    /**
     * Obtiene un mesero por su ID.
     * 
     * @param id Identificador único del mesero.
     * @return Objeto Mesero correspondiente al ID.
     * @throws NegocioException si no se encuentra el mesero o hay error.
     */
    Mesero obtenerMeseroPorId(String id) throws NegocioException;

    /**
     * Registra un nuevo mesero en el sistema.
     * 
     * @param dto DTO con la información del mesero a registrar.
     * @return DTO con la información del mesero registrado.
     * @throws NegocioException si ocurre algún error al registrar.
     */
    MeseroDTO registrarMesero(MeseroDTO dto) throws NegocioException;

    /**
     * Actualiza la información de un mesero existente.
     * 
     * @param dto DTO con la información actualizada del mesero.
     * @return DTO actualizado.
     * @throws NegocioException si ocurre algún error al actualizar.
     */
    MeseroDTO actualizarMesero(MeseroDTO dto) throws NegocioException;

    /**
     * Deshabilita un mesero (por ejemplo, para indicar que ya no trabaja).
     * 
     * @param id ID del mesero a deshabilitar.
     * @return true si se deshabilitó correctamente, false en caso contrario.
     * @throws NegocioException si ocurre algún error durante la deshabilitación.
     */
    boolean deshabilitarMesero(String id) throws NegocioException;

    /**
     * Activa un mesero previamente deshabilitado.
     * 
     * @param id ID del mesero a activar.
     * @return true si se activó correctamente, false en caso contrario.
     * @throws NegocioException si ocurre algún error durante la activación.
     */
    boolean activarMesero(String id) throws NegocioException;

    /**
     * Obtiene la lista completa de meseros registrados.
     * 
     * @return Lista de DTOs de meseros.
     * @throws NegocioException si ocurre algún error al obtener los meseros.
     */
    List<MeseroDTO> obtenerTodos() throws NegocioException;

    /**
     * Obtiene la lista de meseros que están activos.
     * 
     * @return Lista de DTOs de meseros activos.
     * @throws NegocioException si ocurre algún error al obtener los meseros activos.
     */
    List<MeseroDTO> obtenerMeserosActivos() throws NegocioException;

    /**
     * Busca un mesero por su ID y devuelve su DTO.
     * 
     * @param id ID del mesero a buscar.
     * @return DTO del mesero encontrado.
     * @throws NegocioException si no se encuentra el mesero o hay error.
     */
    MeseroDTO buscarMeseroPorId(String id) throws NegocioException;
}