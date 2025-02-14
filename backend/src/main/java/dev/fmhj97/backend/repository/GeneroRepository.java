package dev.fmhj97.backend.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dev.fmhj97.backend.model.Genero;

import java.util.List;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Integer> {
    // Obtener todos los géneros de la base de datos.
    @SuppressWarnings("null")
    @Bean
    List<Genero> findAll();

    // Buscar un género por id.
    Genero findById(int id);

    // Buscar géneros por nombre (ignorando mayúsculas y minúsculas).
    List<Genero> findByNombreContainingIgnoreCase(String nombre);

    // Guardar un género.
    @SuppressWarnings({ "null", "unchecked" })
    @Transactional
    Genero save(Genero genero);

    // Eliminar un género.
    @SuppressWarnings("null")
    @Transactional
    void delete(Genero genero);

    // Eliminar un género por id.
    @Transactional
    void deleteById(int id);
}
