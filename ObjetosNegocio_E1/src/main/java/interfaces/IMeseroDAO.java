/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;


import Pojos.Mesero;
import exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author Gael
 */
/**
 * Interfaz DAO para manejar operaciones de acceso a datos sobre la entidad {@link Mesero}.
 * Incluye métodos para registrar, actualizar, buscar, habilitar y deshabilitar meseros.
 */
public interface IMeseroDAO {

    /**
     * Obtiene un mesero por su ID como cadena.
     * @param id Identificador del mesero.
     * @return Objeto {@link Mesero} correspondiente al ID.
     * @throws PersistenciaException Si ocurre un error durante la búsqueda.
     */
    public Mesero obtenerMeseroPorIdString(String id) throws PersistenciaException;

    /**
     * Registra un nuevo mesero en la base de datos.
     * @param mesero Mesero a registrar.
     * @return El mesero registrado.
     * @throws PersistenciaException Si ocurre un error al insertar el registro.
     */
    public Mesero registrarMesero(Mesero mesero) throws PersistenciaException;

    /**
     * Actualiza los datos de un mesero existente.
     * @param mesero Mesero con los datos actualizados.
     * @return El mesero actualizado.
     * @throws PersistenciaException Si ocurre un error durante la actualización.
     */
    public Mesero actualizarMesero(Mesero mesero) throws PersistenciaException;

    /**
     * Deshabilita un mesero por su ID.
     * @param id ID del mesero a deshabilitar.
     * @return {@code true} si la operación fue exitosa.
     * @throws PersistenciaException Si ocurre un error en la operación.
     */
    public boolean deshabilitarMesero(Object id) throws PersistenciaException;

    /**
     * Activa un mesero previamente deshabilitado.
     * @param id ID del mesero a activar.
     * @return {@code true} si la operación fue exitosa.
     * @throws PersistenciaException Si ocurre un error en la operación.
     */
    public boolean activarMesero(Object id) throws PersistenciaException;

    /**
     * Obtiene todos los meseros registrados.
     * @return Lista de todos los meseros.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    public List<Mesero> obtenerTodos() throws PersistenciaException;

    /**
     * Obtiene todos los meseros que están activos.
     * @return Lista de meseros activos.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    public List<Mesero> obtenerMeserosActivos() throws PersistenciaException;

    /**
     * Busca un mesero por su ID.
     * @param id ID del mesero.
     * @return Mesero correspondiente al ID.
     * @throws PersistenciaException Si ocurre un error en la búsqueda.
     */
    public Mesero buscarPorId(String id) throws PersistenciaException;
}
