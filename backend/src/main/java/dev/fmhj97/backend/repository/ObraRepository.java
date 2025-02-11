package dev.fmhj97.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.fmhj97.backend.model.Obra;

import java.util.Date;
import java.util.List;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Integer> {
    // Buscar por título exacto
    List<Obra> findByTitulo(String titulo);

    // Buscar por parte del título (contiene)
    List<Obra> findByTituloContaining(String titulo);

    // Buscar por fecha de publicación
    List<Obra> findByFechaPublicacion(Date fechaPublicacion);

    // Buscar por autor
    List<Obra> findByAutorId(int idAutor);

    // Buscar por género
    List<Obra> findByGeneroObrasGeneroId(int idGenero);
}
