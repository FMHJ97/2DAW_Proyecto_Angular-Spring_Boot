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

    // Las obras favoritas de un usuario.
    List<RelatoUsuario> findByUsuarioIdAndFavoritoTrue(int usuarioId);

    // Las obras que le gustan a un usuario.
    List<RelatoUsuario> findByUsuarioIdAndMeGustaTrue(int usuarioId);

    // Las obras que ha leído un usuario.
    List<RelatoUsuario> findByUsuarioIdAndUltimaLecturaNotNull(int usuarioId);

    // Eliminar todas las interacciones de un usuario.
    @Transactional
    void deleteByUsuarioId(int usuarioId);

    // Eliminar todas las interacciones de un relato.
    @Transactional
    void deleteByRelatoId(int relatoId);
}