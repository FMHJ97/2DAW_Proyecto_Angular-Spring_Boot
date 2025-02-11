package dev.fmhj97.backend.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.fmhj97.backend.model.Obra;
import jakarta.transaction.Transactional;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Serializable> {
    @SuppressWarnings("null")
    @Bean
    public abstract List<Obra> findAll();

    public abstract Obra findById(int id);

    public abstract Obra findAllByAutor(String autor);

    @SuppressWarnings({ "unchecked", "null" })
    @Transactional
    public abstract Obra save(Obra o);

    @SuppressWarnings("null")
    @Transactional
    public abstract void delete(Obra o);

    @Transactional
    public abstract void deleteById(int id);
}
