package com.example.standalone.service.impl;

import com.example.standalone.exception.NotFoundException;
import com.example.standalone.model.Usuario;
import com.example.standalone.repository.UsuarioRepository;
import com.example.standalone.service.UsuarioService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementación de la capa de servicio para Usuarios.
 */
@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    /** Lista todos los usuarios. */
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return repository.findAll();
    }

    /** Obtiene un usuario por id o lanza NotFoundException. */
    @Override
    @Transactional(readOnly = true)
    public Usuario obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado con id " + id));
    }

    /** Crea un nuevo usuario. Valida unicidad de email por restricción de BD. */
    @Override
    public Usuario crear(Usuario usuario) {
        // Normalización simple
        usuario.setEmail(usuario.getEmail().trim().toLowerCase());
        return repository.save(usuario);
    }

    /** Actualiza un usuario existente. */
    @Override
    public Usuario actualizar(Long id, Usuario cambios) {
        Usuario existente = obtenerPorId(id);
        existente.setNombre(cambios.getNombre());
        existente.setEmail(cambios.getEmail().trim().toLowerCase());
        existente.setRol(cambios.getRol());
        return repository.save(existente);
    }

    /** Elimina un usuario por id. */
    @Override
    public void eliminar(Long id) {
        Usuario existente = obtenerPorId(id);
        repository.delete(existente);
    }
}
