package com.example.standalone.service;

import com.example.standalone.model.Usuario;

import java.util.List;

/**
 * Capa de servicio: define la lógica de negocio para Usuarios.
 */
public interface UsuarioService {
    List<Usuario> listar();
    Usuario obtenerPorId(Long id);
    Usuario crear(Usuario usuario);
    Usuario actualizar(Long id, Usuario usuario);
    void eliminar(Long id);
}
