package com.example.standalone.repository;

import com.example.standalone.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Capa de acceso a datos. Hereda operaciones CRUD de JpaRepository.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
