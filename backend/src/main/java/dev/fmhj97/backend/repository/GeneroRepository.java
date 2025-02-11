package dev.fmhj97.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.fmhj97.backend.model.Genero;

import java.util.List;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Integer> {
    // Buscar por nombre exacto
    Genero findByNombre(String nombre);

    // Buscar por parte del nombre (contiene)
    List<Genero> findByNombreContaining(String nombre);
}
