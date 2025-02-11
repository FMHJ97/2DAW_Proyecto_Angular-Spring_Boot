package dev.fmhj97.backend.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.fmhj97.backend.model.Autor;
import jakarta.transaction.Transactional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Serializable> {
    @SuppressWarnings("null")
    @Bean
    public abstract List<Autor> findAll();

    public abstract Autor findById(int id);

    public abstract Autor findByFullName(String fullName);

    @SuppressWarnings({ "unchecked", "null" })
    @Transactional
    public abstract Autor save(Autor o);

    @SuppressWarnings("null")
    @Transactional
    public abstract void delete(Autor o);

    @Transactional
    public abstract void deleteById(int id);
}
