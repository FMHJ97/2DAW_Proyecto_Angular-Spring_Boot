package dev.fmhj97.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.fmhj97.backend.model.ObraUsuario;

import java.util.List;

@Repository
public interface ObraUsuarioRepository extends JpaRepository<ObraUsuario, Integer> {
    // Buscar por usuario
    List<ObraUsuario> findByUsuarioId(int usuarioId);

    // Buscar por obra
    List<ObraUsuario> findByObraId(int obraId);

    // Buscar por estado de lectura
    List<ObraUsuario> findByEstadoLectura(String estadoLectura);

    // Buscar por favorito
    List<ObraUsuario> findByFavorito(boolean favorito);

    // Buscar por usuario y estado de lectura
    List<ObraUsuario> findByUsuarioIdAndEstadoLectura(int usuarioId, String estadoLectura);

    // Buscar por usuario y favorito
    List<ObraUsuario> findByUsuarioIdAndFavorito(int usuarioId, boolean favorito);
}
