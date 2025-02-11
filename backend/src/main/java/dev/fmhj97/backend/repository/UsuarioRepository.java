package dev.fmhj97.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.fmhj97.backend.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Buscar por email
    Usuario findByEmail(String email);

    // Buscar por usuario y contraseña
    Usuario findByUsuarioAndPassword(String usuario, String password);
}
