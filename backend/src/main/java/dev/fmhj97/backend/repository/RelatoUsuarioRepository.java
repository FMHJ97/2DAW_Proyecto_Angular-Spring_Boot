package dev.fmhj97.backend.repository;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dev.fmhj97.backend.model.RelatoUsuario;

@Repository
public interface RelatoUsuarioRepository extends JpaRepository<RelatoUsuario, Integer> {
    // Métodos heredados de JpaRepository
    @SuppressWarnings("null")
    @Bean
    List<RelatoUsuario> findAll();

    RelatoUsuario findById(int id);

    @SuppressWarnings({ "null", "unchecked" })
    @Transactional
    RelatoUsuario save(RelatoUsuario relatoUsuario);

    @SuppressWarnings("null")
    @Transactional
    void delete(RelatoUsuario relatoUsuario);

    @Transactional
    void deleteById(int id);

    // Métodos personalizados

    // Obtener todas las interacciones de un usuario.
    List<RelatoUsuario> findByUsuarioId(int usuarioId);

    // Obtener todas las interacciones de un relato.
    List<RelatoUsuario> findByRelatoId(int relatoId);

    // Buscar una interacción específica por usuario y relato.
    RelatoUsuario findByUsuarioIdAndRelatoId(int usuarioId, int relatoId);

    // Eliminar todas las interacciones de un usuario.
    @Transactional
    void deleteByUsuarioId(int usuarioId);

    // Eliminar todas las interacciones de un relato.
    @Transactional
    void deleteByRelatoId(int relatoId);
}