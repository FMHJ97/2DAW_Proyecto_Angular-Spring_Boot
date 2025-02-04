package dev.fmhj97.backend.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import dev.fmhj97.backend.model.Obra;
import jakarta.transaction.Transactional;

public interface ObraRepository extends JpaRepository<Obra, Serializable> {

    @SuppressWarnings("null")
    @Bean
    public abstract List<Obra> findAll();

    public abstract Obra findById(int id);

    @SuppressWarnings({ "null", "unchecked" })
    @Transactional
    public abstract Obra save(Obra obra);

    @SuppressWarnings("null")
    @Transactional
    public abstract void delete(Obra obra);

    @Transactional
    public abstract void deleteById(int id);

}