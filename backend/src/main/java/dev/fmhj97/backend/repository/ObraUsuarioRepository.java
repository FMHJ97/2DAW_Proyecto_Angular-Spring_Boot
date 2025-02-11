package dev.fmhj97.backend.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.fmhj97.backend.model.ObraUsuario;
import jakarta.transaction.Transactional;

@Repository
public interface ObraUsuarioRepository extends JpaRepository<ObraUsuario, Serializable> {
    @SuppressWarnings("null")
    @Bean
    public abstract List<ObraUsuario> findAll();

    public abstract ObraUsuario findById(int id);

    @SuppressWarnings({ "unchecked", "null" })
    @Transactional
    public abstract ObraUsuario save(ObraUsuario o);

    @SuppressWarnings("null")
    @Transactional
    public abstract void delete(ObraUsuario o);

    @Transactional
    public abstract void deleteById(int id);
}
