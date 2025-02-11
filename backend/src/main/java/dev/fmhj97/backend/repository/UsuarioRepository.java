package dev.fmhj97.backend.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.fmhj97.backend.model.Usuario;
import jakarta.transaction.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Serializable> {
    @SuppressWarnings("null")
    @Bean
    public abstract List<Usuario> findAll();

    public abstract Usuario findById(int id);

    public abstract Usuario findByEmail(String email);

    public abstract Usuario findByUserAndPass(String usuario, String password);

    @SuppressWarnings({ "unchecked", "null" })
    @Transactional
    public abstract Usuario save(Usuario o);

    @SuppressWarnings("null")
    @Transactional
    public abstract void delete(Usuario o);

    @Transactional
    public abstract void deleteById(int id);
}
