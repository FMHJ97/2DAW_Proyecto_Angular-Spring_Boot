package dev.fmhj97.backend.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.fmhj97.backend.model.Genero;
import jakarta.transaction.Transactional;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Serializable> {
    @SuppressWarnings("null")
    @Bean
    public abstract List<Genero> findAll();

    public abstract Genero findById(int id);

    @SuppressWarnings({ "unchecked", "null" })
    @Transactional
    public abstract Genero save(Genero o);

    @SuppressWarnings("null")
    @Transactional
    public abstract void delete(Genero o);

    @Transactional
    public abstract void deleteById(int id);
}
