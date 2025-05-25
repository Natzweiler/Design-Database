/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author Gael
 */
/**
 * Clase utilitaria que gestiona la conexión a la base de datos MongoDB.
 * Utiliza el patrón Singleton para asegurar que exista una única instancia del cliente.
 * Proporciona acceso a la base de datos {@code GestionRestaurantes} con soporte para POJOs.
 */
public class ConexionMongo {

    /** Cliente de MongoDB utilizado para acceder a la base de datos. */
    private static MongoClient mongoClient = null;

    /** URL de conexión a MongoDB. */
    private static final String URL = "mongodb://localhost:27017";

    /** Nombre de la base de datos utilizada en la aplicación. */
    private static final String DATABASE_NAME = "GestionRestaurantes";

    /**
     * Constructor privado para evitar la instanciación de la clase.
     */
    private ConexionMongo() {}

    /**
     * Retorna una instancia de la base de datos MongoDB.
     * Inicializa la conexión si aún no ha sido creada.
     * Utiliza un codec registry para mapear objetos POJO automáticamente.
     *
     * @return Instancia de {@link MongoDatabase} configurada para trabajar con POJOs.
     */
    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            // Registro de codecs para soporte de POJOs
            CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
            );

            // Configuración del cliente MongoDB
            MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(URL))
                .codecRegistry(pojoCodecRegistry)
                .build();

            mongoClient = MongoClients.create(settings);

            // Retorna la base de datos con codecs habilitados
            return mongoClient.getDatabase(DATABASE_NAME).withCodecRegistry(pojoCodecRegistry);
        }

        // Retorna la base de datos si ya existe una conexión activa
        return mongoClient.getDatabase(DATABASE_NAME);
    }
}
