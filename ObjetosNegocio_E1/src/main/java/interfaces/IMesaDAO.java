/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import Pojos.Mesa;
import exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author Gael
 */
/**
 * Interfaz que define los métodos de acceso a datos (DAO) para la entidad {@link Mesa}.
 * Proporciona operaciones CRUD y de actualización de estado.
 */
public interface IMesaDAO {

    /**
     * Obtiene una mesa por su número de mesa.
     * @param numeroMesa Número de la mesa a buscar.
     * @return La mesa correspondiente al número proporcionado.
     * @throws PersistenciaException Si ocurre un error al acceder a la base de datos.
     */
    public Mesa obtenerMesaPorNumeroMesa(Integer numeroMesa) throws PersistenciaException;

    /**
     * Registra una nueva mesa en la base de datos.
     * @param mesa Objeto {@link Mesa} a registrar.
     * @return La mesa registrada.
     * @throws PersistenciaException Si ocurre un error al insertar en la base de datos.
     */
    public Mesa registrarMesa(Mesa mesa) throws PersistenciaException;

    /**
     * Obtiene todas las mesas almacenadas.
     * @return Lista de todas las mesas.
     * @throws PersistenciaException Si ocurre un error al consultar la base de datos.
     */
    public List<Mesa> obtenerTodasLasMesas() throws PersistenciaException;

    /**
     * Actualiza el estado de disponibilidad de una mesa.
     * @param numeroMesa Número de la mesa.
     * @param disponible {@code true} si la mesa está disponible, {@code false} si está ocupada.
     * @return {@code true} si la actualización fue exitosa.
     * @throws PersistenciaException Si ocurre un error al actualizar en la base de datos.
     */
    public boolean actualizarDisponibilidadMesa(int numeroMesa, boolean disponible) throws PersistenciaException;
}
