package dev.fmhj97.backend.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import dev.fmhj97.backend.model.Autor;
import jakarta.transaction.Transactional;

public interface AutorRepository extends JpaRepository<Autor, Serializable> {

    @SuppressWarnings("null")
    @Bean
    public abstract List<Autor> findAll();

    public abstract Autor findById(int id);

    @SuppressWarnings({ "null", "unchecked" })
    @Transactional
    public abstract Autor save(Autor autor);

    @SuppressWarnings("null")
    @Transactional
    public abstract void delete(Autor autor);

    @Transactional
    public abstract void deleteById(int id);

}