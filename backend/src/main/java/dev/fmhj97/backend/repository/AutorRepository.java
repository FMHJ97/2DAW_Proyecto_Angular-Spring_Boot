package dev.fmhj97.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.fmhj97.backend.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer> {
    // Métodos adicionales personalizados, si es necesario
    Autor findByFullName(String fullName);
}
