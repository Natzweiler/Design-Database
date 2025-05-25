package Persistencia;

import Pojos.Reservacion;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import exception.PersistenciaException;
import interfaces.IReservacionDAO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.bson.types.ObjectId;

/**
 * Clase DAO que maneja la persistencia de objetos {@link Reservacion} en una base de datos MongoDB.
 * Implementa la interfaz {@link IReservacionDAO}.
 * Utiliza la colección "reservaciones" para almacenar y consultar documentos de tipo Reservacion.
 * Sigue el patrón Singleton para asegurar una única instancia.
 */
public class ReservacionDAO implements IReservacionDAO {
    private static ReservacionDAO instance;

    private final MongoCollection<Reservacion> coleccion;

    /**
     * Constructor privado que inicializa la colección de reservaciones desde la conexión a MongoDB.
     */
    public ReservacionDAO() {
        this.coleccion = Conexion.ConexionMongo.getDatabase().getCollection("reservaciones", Reservacion.class);
    }

    /**
     * Devuelve la única instancia de {@code ReservacionDAO}.
     * @return Instancia singleton de {@code ReservacionDAO}.
     */
    public static ReservacionDAO getInstanceDAO() {
        if (instance == null) {
            instance = new ReservacionDAO();
        }
        return instance;
    }

    /**
     * Registra una nueva reservación en la base de datos.
     * @param reservacion Objeto {@code Reservacion} a registrar.
     * @return La misma reservación registrada.
     * @throws PersistenciaException Si ocurre un error al insertar en la base de datos.
     */
    @Override
    public Reservacion registrarReservacion(Reservacion reservacion) throws PersistenciaException {
        try {
            coleccion.insertOne(reservacion);
            return reservacion;
        } catch (Exception e) {
            throw new PersistenciaException("Error al registrar la reservación.", e);
        }
    }

    /**
     * Lista todas las reservaciones almacenadas en la base de datos.
     * @return Lista de objetos {@code Reservacion}.
     * @throws PersistenciaException Si ocurre un error al realizar la consulta.
     */
    @Override
    public List<Reservacion> listarReservaciones() throws PersistenciaException {
        try {
            return coleccion.find().into(new ArrayList<>());
        } catch (Exception e) {
            throw new PersistenciaException("Error al listar reservaciones.", e);
        }
    }

    /**
     * Verifica si una mesa está disponible para una fecha y hora específicas.
     * @param numeroMesa Número de la mesa a verificar.
     * @param fecha Fecha de la reservación.
     * @param hora Hora de la reservación.
     * @return {@code true} si la mesa está disponible, {@code false} si ya está reservada.
     * @throws PersistenciaException Si ocurre un error al realizar la consulta.
     */
    @Override
    public boolean estadoMesaDisponible(Integer numeroMesa, LocalDate fecha, LocalTime hora) throws PersistenciaException {
        try {
            Reservacion reservacion = coleccion.find(
                Filters.and(
                    Filters.eq("mesa.numeroMesa", numeroMesa),
                    Filters.eq("fecha", fecha),
                    Filters.eq("hora", hora)
                )
            ).first();

            return reservacion == null; 

        } catch (Exception e) {
            throw new PersistenciaException("Error al verificar disponibilidad de la mesa.", e);
        }
    }
}
