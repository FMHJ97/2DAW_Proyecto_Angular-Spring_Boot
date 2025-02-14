package dev.fmhj97.backend.repository;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dev.fmhj97.backend.model.Relato;

@Repository
public interface RelatoRepository extends JpaRepository<Relato, Integer> {
    // Métodos heredados de JpaRepository
    @SuppressWarnings("null")
    @Bean
    List<Relato> findAll();

    Relato findById(int id);

    @SuppressWarnings({ "null", "unchecked" })
    @Transactional
    Relato save(Relato relato);

    @SuppressWarnings("null")
    @Transactional
    void delete(Relato relato);

    @Transactional
    void deleteById(int id);

    // Métodos personalizados

    // Obtener todos los relatos de un usuario.
    List<Relato> findByUsuarioId(int usuarioId);

    // Buscar relatos por título (ignorando mayúsculas y minúsculas).
    List<Relato> findByTituloContainingIgnoreCase(String titulo);

    // Obtener los relatos más recientes primero.
    List<Relato> findAllByOrderByFechaPublicacionDesc();

    // Obtener los relatos más antiguos primero.
    List<Relato> findAllByOrderByFechaPublicacionAsc();
}