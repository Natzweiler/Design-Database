/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;


import Interfaces.IMeseroBO;
import Mappers.MeseroMapper;
import Persistencia.MeseroDAO;
import Pojos.Mesero;
import dtos.MeseroDTO;
import exception.PersistenciaException;
import interfaces.IMeseroDAO;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import negocio.exception.NegocioException;
import org.bson.types.ObjectId;

/**
 *
 * @author Gael
 */
/**
 * Clase de negocio para la gestión de meseros.
 * Implementa la interfaz IMeseroBO y actúa como intermediario entre la capa
 * de presentación y la capa de persistencia.
 */
public class MeseroBO implements IMeseroBO {

    private IMeseroDAO meseroDAO;

    /**
     * Constructor que recibe una instancia de IMeseroDAO.
     * Por defecto, obtiene la instancia singleton de MeseroDAO.
     * 
     * @param meseroDAO DAO para acceso a datos de meseros.
     */
    public MeseroBO(IMeseroDAO meseroDAO) {
        this.meseroDAO = MeseroDAO.getInstanceDAO();
    }

    /**
     * Obtiene un mesero por su ID.
     * 
     * @param id ID del mesero en formato String.
     * @return Mesero encontrado.
     * @throws NegocioException si ocurre un error en la capa de persistencia.
     */
    public Mesero obtenerMeseroPorId(String id) throws NegocioException {
        try {
            return meseroDAO.obtenerMeseroPorIdString(id);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener el mesero con ID: " + id, e);
        }
    }

    /**
     * Registra un nuevo mesero validando los datos obligatorios.
     * 
     * @param dto DTO con datos del mesero a registrar.
     * @return DTO del mesero registrado.
     * @throws NegocioException si los datos son inválidos o ocurre un error.
     */
    public MeseroDTO registrarMesero(MeseroDTO dto) throws NegocioException {
        try {
            if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
                throw new NegocioException("El nombre del mesero no puede estar vacío.");
            }
            if (dto.getTelefono() == null || dto.getTelefono().trim().isEmpty()) {
                throw new NegocioException("El teléfono no puede estar vacío.");
            }
            if (dto.getFechaNacimiento() == null) {
                throw new NegocioException("La fecha de nacimiento es obligatoria.");
            }
            if (dto.getFechaNacimiento().isAfter(LocalDate.now())) {
                throw new NegocioException("La fecha de nacimiento no puede ser futura.");
            }
            if (dto.getFechaNacimiento().isBefore(LocalDate.of(1900, 1, 1))) {
                throw new NegocioException("La fecha de nacimiento es demasiado antigua.");
            }

            Mesero mesero = MeseroMapper.toEntity(dto);
            mesero.setEstado(true); 
            Mesero registrado = meseroDAO.registrarMesero(mesero);

            return MeseroMapper.toDTO(registrado);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al registrar el mesero", e);
        }
    }

    /**
     * Actualiza un mesero existente con validaciones similares al registro.
     * 
     * @param dto DTO con los datos actualizados del mesero.
     * @return DTO del mesero actualizado.
     * @throws NegocioException si los datos son inválidos o ocurre un error.
     */
    public MeseroDTO actualizarMesero(MeseroDTO dto) throws NegocioException {
        try {
            if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
                throw new NegocioException("El nombre del mesero no puede estar vacío.");
            }
            if (dto.getTelefono() == null || dto.getTelefono().trim().isEmpty()) {
                throw new NegocioException("El teléfono no puede estar vacío.");
            }
            if (dto.getFechaNacimiento() == null) {
                throw new NegocioException("La fecha de nacimiento es obligatoria.");
            }
            if (dto.getFechaNacimiento().isAfter(LocalDate.now())) {
                throw new NegocioException("La fecha de nacimiento no puede ser futura.");
            }
            if (dto.getFechaNacimiento().isBefore(LocalDate.of(1900, 1, 1))) {
                throw new NegocioException("La fecha de nacimiento es demasiado antigua.");
            }

            Mesero mesero = MeseroMapper.toEntity(dto);
            Mesero actualizado = meseroDAO.actualizarMesero(mesero);
            return MeseroMapper.toDTO(actualizado);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar el mesero", e);
        }
    }

    /**
     * Deshabilita un mesero por su ID.
     * 
     * @param id ID del mesero en formato String.
     * @return true si la deshabilitación fue exitosa.
     * @throws NegocioException si ocurre un error en persistencia.
     */
    public boolean deshabilitarMesero(String id) throws NegocioException {
        try {
            if (id.isEmpty()) {
                throw new NegocioException("No fue posible deshabilitar el mesero.");
            }
            return meseroDAO.deshabilitarMesero(id);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al deshabilitar el mesero", e);
        }
    }

    /**
     * Activa un mesero por su ID.
     * 
     * @param id ID del mesero en formato String.
     * @return true si la activación fue exitosa.
     * @throws NegocioException si ocurre un error en persistencia.
     */
    public boolean activarMesero(String id) throws NegocioException {
        try {
           if (id.isEmpty()) {
                throw new NegocioException("No fue posible habilitar el mesero.");
            }
            return meseroDAO.activarMesero(id);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al activar el mesero", e);
        }
    }

    /**
     * Obtiene la lista completa de meseros.
     * 
     * @return Lista de MeseroDTO.
     * @throws NegocioException si ocurre un error en persistencia.
     */
    public List<MeseroDTO> obtenerTodos() throws NegocioException {
        try {
            List<Mesero> meseros = meseroDAO.obtenerTodos();
            return meseros.stream()
                    .map(MeseroMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener la lista de meseros", e);
        }
    }

    /**
     * Obtiene la lista de meseros activos (habilitados).
     * 
     * @return Lista de MeseroDTO activos.
     * @throws NegocioException si ocurre un error en persistencia.
     */
    public List<MeseroDTO> obtenerMeserosActivos() throws NegocioException {
        try {
            List<Mesero> lista = meseroDAO.obtenerMeserosActivos();
            return lista.stream()
                    .map(MeseroMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener meseros activos", e);
        }
    }

    /**
     * Busca un mesero por su ID y devuelve su DTO.
     * 
     * @param id ID del mesero.
     * @return MeseroDTO encontrado.
     * @throws NegocioException si no se encuentra o hay error.
     */
    public MeseroDTO buscarMeseroPorId(String id) throws NegocioException {
        try {
            Mesero mesero = meseroDAO.buscarPorId(id);
            if (mesero == null) throw new NegocioException("Mesero no encontrado.");
            return MeseroMapper.toDTO(mesero);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar el mesero por ID", e);
        }
    }
}


