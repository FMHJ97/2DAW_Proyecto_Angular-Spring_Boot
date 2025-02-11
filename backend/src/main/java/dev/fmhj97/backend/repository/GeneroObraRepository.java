package dev.fmhj97.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.fmhj97.backend.model.GeneroObra;

import java.util.List;

@Repository
public interface GeneroObraRepository extends JpaRepository<GeneroObra, Integer> {
    // Buscar por género
    List<GeneroObra> findByGeneroId(int idGenero);

    // Buscar por obra
    List<GeneroObra> findByObraId(int idObra);
}