/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Conexion.ConexionMongo;
import Pojos.Mesa;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import exception.PersistenciaException;
import interfaces.IMesaDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Gael
 */
/**
 * Clase DAO para manejar operaciones de persistencia relacionadas con la entidad Mesa en MongoDB.
 * Implementa la interfaz IMesaDAO.
 * Utiliza el patrón Singleton para garantizar una única instancia.
 */
public class MesaDAO implements IMesaDAO {

    private static MesaDAO instance;
    private final MongoCollection<Mesa> coleccion;

    /**
     * Constructor privado. Inicializa la colección 'mesas' desde la base de datos.
     */
    private MesaDAO() {
        this.coleccion = ConexionMongo.getDatabase().getCollection("mesas", Mesa.class);
    }

    /**
     * Devuelve la instancia única de MesaDAO.
     * @return instancia de MesaDAO
     */
    public static MesaDAO getInstanceDAO() {
        if (instance == null) {
            instance = new MesaDAO();
        }
        return instance;
    }

    /**
     * Obtiene una mesa por su número.
     * @param numeroMesa el número de la mesa a buscar.
     * @return objeto Mesa correspondiente.
     * @throws PersistenciaException si no se encuentra la mesa o hay error de base de datos.
     */
    @Override
    public Mesa obtenerMesaPorNumeroMesa(Integer numeroMesa) throws PersistenciaException {
        try {
            Mesa mesa = coleccion.find(Filters.eq("numeroMesa", numeroMesa)).first();
            if (mesa == null) {
                throw new PersistenciaException("No se encontró la mesa con número: " + numeroMesa);
            }
            return mesa;
        } catch (Exception e) {
            throw new PersistenciaException("Error al recuperar la mesa con número: " + numeroMesa, e);
        }
    }

    /**
     * Registra una nueva mesa en la base de datos.
     * @param mesa la mesa a registrar.
     * @return la mesa registrada.
     * @throws PersistenciaException si ocurre un error al registrar.
     */
    @Override
    public Mesa registrarMesa(Mesa mesa) throws PersistenciaException {
        try {
            coleccion.insertOne(mesa);
            return mesa;
        } catch (Exception e) {
            throw new PersistenciaException("Error al registrar la mesa", e);
        }
    }

    /**
     * Obtiene todas las mesas registradas.
     * @return lista de todas las mesas.
     * @throws PersistenciaException si ocurre un error al obtenerlas.
     */
    @Override
    public List<Mesa> obtenerTodasLasMesas() throws PersistenciaException {
        try {
            return coleccion.find().into(new ArrayList<>());
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener todas las mesas", e);
        }
    }

    /**
     * Actualiza la disponibilidad de una mesa específica.
     * @param numeroMesa número de la mesa.
     * @param disponible nuevo estado de disponibilidad.
     * @return true si la actualización fue exitosa.
     * @throws PersistenciaException si ocurre un error o no se modifica ninguna mesa.
     */
    @Override
    public boolean actualizarDisponibilidadMesa(int numeroMesa, boolean disponible) throws PersistenciaException {
        try {
            UpdateResult resultado = coleccion.updateOne(
                Filters.eq("numeroMesa", numeroMesa),
                Updates.set("disponible", disponible)
            );
            if (resultado.getModifiedCount() == 0) {
                throw new PersistenciaException("No se actualizó ninguna mesa, verifica el número de mesa.");
            }
            return true;
        } catch (Exception e) {
            throw new PersistenciaException("Error al actualizar la disponibilidad de la mesa", e);
        }
    }

    /**
     * Obtiene la lista de mesas que están disponibles.
     * @return lista de mesas disponibles.
     * @throws PersistenciaException si ocurre un error al obtenerlas.
     */
    public List<Mesa> obtenerMesasDisponibles() throws PersistenciaException {
        try {
            return coleccion.find(Filters.eq("disponible", true)).into(new ArrayList<>());
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener mesas disponibles", e);
        }
    }
}
