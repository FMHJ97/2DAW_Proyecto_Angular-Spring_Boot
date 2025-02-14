package dev.fmhj97.backend.repository;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dev.fmhj97.backend.model.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    // Métodos heredados de JpaRepository
    @SuppressWarnings("null")
    @Bean
    List<Comentario> findAll();

    Comentario findById(int id);

    @SuppressWarnings({ "null", "unchecked" })
    @Transactional
    Comentario save(Comentario comentario);

    @SuppressWarnings("null")
    @Transactional
    void delete(Comentario comentario);

    @Transactional
    void deleteById(int id);

    // Métodos personalizados

    // Obtener todos los comentarios de un relato.
    List<Comentario> findByRelatoId(int relatoId);

    // Obtener todos los comentarios de un usuario.
    List<Comentario> findByUsuarioId(int usuarioId);

    // Eliminar todos los comentarios de un relato.
    @Transactional
    void deleteByRelatoId(int relatoId);

    // Eliminar todos los comentarios de un usuario.
    @Transactional
    void deleteByUsuarioId(int usuarioId);
}