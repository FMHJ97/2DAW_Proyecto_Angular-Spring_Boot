package dev.fmhj97.backend.repository;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dev.fmhj97.backend.model.RelatoGenero;

@Repository
public interface RelatoGeneroRepository extends JpaRepository<RelatoGenero, Integer> {
    // Métodos heredados de JpaRepository
    @SuppressWarnings("null")
    @Bean
    List<RelatoGenero> findAll();

    RelatoGenero findById(int id);

    @SuppressWarnings({ "null", "unchecked" })
    @Transactional
    RelatoGenero save(RelatoGenero relatoGenero);

    @SuppressWarnings("null")
    @Transactional
    void delete(RelatoGenero relatoGenero);

    @Transactional
    void deleteById(int id);

    // Métodos personalizados

    // Obtener todos los géneros asociados a un relato.
    List<RelatoGenero> findByRelatoId(int relatoId);

    // Obtener todos los relatos asociados a un género.
    List<RelatoGenero> findByGeneroId(int generoId);

    // Obtener todos los géneros asociados a generos.
    List<RelatoGenero> findByGeneroIdIn(List<Integer> generoIds);

    // Eliminar todas las asociaciones de un relato.
    @Transactional
    void deleteByRelatoId(int relatoId);

    // Eliminar todas las asociaciones de un género.
    @Transactional
    void deleteByGeneroId(int generoId);
}