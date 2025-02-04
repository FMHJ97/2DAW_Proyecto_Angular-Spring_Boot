package dev.fmhj97.backend.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import dev.fmhj97.backend.model.Usuario;
import jakarta.transaction.Transactional;

public interface UsuarioRepository extends JpaRepository<Usuario, Serializable> {

    @SuppressWarnings("null")
    @Bean
    public abstract List<Usuario> findAll();

    public abstract Usuario findById(int id);

    public abstract Usuario findByUsuario(String usuario);

    public abstract Usuario findByEmail(String email);

    @SuppressWarnings({ "null", "unchecked" })
    @Transactional
    public abstract Usuario save(Usuario usuario);

    @SuppressWarnings("null")
    @Transactional
    public abstract void delete(Usuario usuario);

    @Transactional
    public abstract void deleteById(int id);

    @Transactional
    public abstract void deleteByUsuario(String usuario);

    @Transactional
    public abstract void deleteByEmail(String email);

}
