/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Conexion.ConexionMongo;
import Pojos.Mesero;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import exception.PersistenciaException;
import interfaces.IMeseroDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author Gael
 */
/**
 * Clase DAO para manejar operaciones de persistencia relacionadas con la entidad Mesero en MongoDB.
 * Implementa la interfaz IMeseroDAO y sigue el patrón Singleton.
 */
public class MeseroDAO implements IMeseroDAO {

    private static MeseroDAO instance;
    private final MongoCollection<Mesero> coleccion;

    /**
     * Constructor privado que inicializa la colección 'meseros'.
     */
    private MeseroDAO() {
        this.coleccion = ConexionMongo.getDatabase().getCollection("meseros", Mesero.class);
    }

    /**
     * Devuelve la instancia única de MeseroDAO.
     * @return instancia de MeseroDAO
     */
    public static MeseroDAO getInstanceDAO() {
        if (instance == null) {
            instance = new MeseroDAO();
        }
        return instance;
    }

    /**
     * Registra un nuevo mesero en la base de datos.
     * @param mesero objeto Mesero a registrar.
     * @return mesero registrado.
     * @throws PersistenciaException si ocurre un error al registrar.
     */
    @Override
    public Mesero registrarMesero(Mesero mesero) throws PersistenciaException {
        try {
            coleccion.insertOne(mesero);
            return mesero;
        } catch (Exception e) {
            throw new PersistenciaException("Error al registrar el mesero.", e);
        }
    }

    /**
     * Actualiza un mesero en la base de datos.
     * @param mesero objeto actualizado.
     * @return el mesero actualizado.
     * @throws PersistenciaException si ocurre un error o no se encuentra el mesero.
     */
    
    @Override
    public Mesero actualizarMesero(Mesero mesero) throws PersistenciaException {
        try {
            Bson filtro = Filters.eq("_id", mesero.getId());
            UpdateResult result = coleccion.replaceOne(filtro, mesero);
            if (result.getModifiedCount() == 0) {
                throw new PersistenciaException("No se encontró el mesero para actualizar.");
            }
            return mesero;
        } catch (Exception e) {
            throw new PersistenciaException("Error al actualizar el mesero.", e);
        }
    }
    
    /**
     * Deshabilita (inactiva) un mesero.
     * @param id identificador del mesero.
     * @return true si la operación fue exitosa.
     * @throws PersistenciaException si ocurre un error.
     */
    
    @Override
    public boolean deshabilitarMesero(Object id) throws PersistenciaException {
        try {
            //cambiar lo del bo aqui
            ObjectId objectid = new ObjectId(id.toString());
            Bson filtro = Filters.eq("_id", objectid);
            Bson actualizacion = Updates.set("estado", false);
            return coleccion.updateOne(filtro, actualizacion).getModifiedCount() > 0;
        } catch (Exception e) {
            throw new PersistenciaException("Error al deshabilitar el mesero.", e);
        }
    }

    /**
     * Activa un mesero previamente deshabilitado.
     * @param id identificador del mesero.
     * @return true si fue activado con éxito.
     * @throws PersistenciaException si ocurre un error.
     */
    @Override
    public boolean activarMesero(Object id) throws PersistenciaException {
        try {
              ObjectId objectid = new ObjectId(id.toString());
            Bson filtro = Filters.eq("_id", objectid);
            Bson actualizacion = Updates.set("estado", true);
            return coleccion.updateOne(filtro, actualizacion).getModifiedCount() > 0;
        } catch (Exception e) {
            throw new PersistenciaException("Error al activar el mesero.", e);
        }
    }

    /**
     * Obtiene un mesero por su ID en formato String.
     * @param id ID del mesero en formato String.
     * @return objeto Mesero encontrado.
     * @throws PersistenciaException si el ID es inválido o no se encuentra.
     */
    public Mesero obtenerMeseroPorIdString(String id) throws PersistenciaException {
        try {
            ObjectId objectId = new ObjectId(id);
            Mesero mesero = coleccion.find(Filters.eq("_id", objectId)).first();
            if (mesero == null) {
                throw new PersistenciaException("No se encontró mesero con ID: " + id);
            }
            return mesero;
        } catch (IllegalArgumentException iae) {
            throw new PersistenciaException("ID inválido: " + id, iae);
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar el mesero por ID.", e);
        }
    }

    /**
     * Obtiene todos los meseros registrados.
     * @return lista de meseros.
     * @throws PersistenciaException si ocurre un error al obtenerlos.
     */
    @Override
    public List<Mesero> obtenerTodos() throws PersistenciaException {
        try {
            return coleccion.find().into(new ArrayList<>());
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener todos los meseros.", e);
        }
    }

    /**
     * Obtiene todos los meseros que estén activos.
     * @return lista de meseros activos.
     * @throws PersistenciaException si ocurre un error al obtenerlos.
     */
    @Override
    public List<Mesero> obtenerMeserosActivos() throws PersistenciaException {
        try {
            return coleccion.find(Filters.eq("estado", true)).into(new ArrayList<>());
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener meseros activos.", e);
        }
    }

    /**
     * Busca un mesero por su ID en formato String.
     * @param id ID del mesero.
     * @return objeto Mesero encontrado o null.
     * @throws PersistenciaException si ocurre un error.
     */
    @Override
    public Mesero buscarPorId(String id) throws PersistenciaException {
        try {
            ObjectId objectId = new ObjectId(id);
            return coleccion.find(Filters.eq("_id", objectId)).first();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar el mesero por ID", e);
        }
    }
}



