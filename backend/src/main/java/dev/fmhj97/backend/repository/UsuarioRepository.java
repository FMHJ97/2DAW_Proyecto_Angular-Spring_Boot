package dev.fmhj97.backend.repository;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dev.fmhj97.backend.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Obtener todos los usuarios.
    @SuppressWarnings("null")
    @Bean
    List<Usuario> findAll();

    // Buscar por id.
    Usuario findById(int id);

    // Buscar por email.
    Usuario findByEmail(String email);

    // Buscar por usuario.
    Usuario findByUsuario(String usuario);

    // Buscar por usuario y password (BCrypt).
    Usuario findByUsuarioAndPassword(String usuario, String password);

    // Guardar un usuario.
    @SuppressWarnings({ "null", "unchecked" })
    @Transactional
    Usuario save(Usuario usuario);

    // Eliminar un usuario.
    @SuppressWarnings("null")
    @Transactional
    void delete(Usuario usuario);

    // Eliminar un usuario por id.
    @Transactional
    void deleteById(int id);
}
