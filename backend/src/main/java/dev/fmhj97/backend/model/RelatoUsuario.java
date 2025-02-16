package dev.fmhj97.backend.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "relato_usuario")
@NamedQuery(name = "RelatoUsuario.findAll", query = "SELECT ru FROM RelatoUsuario ru")
public class RelatoUsuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_relato", nullable = false)
    private Relato relato;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "me_gusta", nullable = false)
    private boolean meGusta;

    @Column(name = "favorito", nullable = false)
    private boolean favorito;

    // Constructores
    public RelatoUsuario() {
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Relato getRelato() {
        return relato;
    }

    public void setRelato(Relato relato) {
        this.relato = relato;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isMeGusta() {
        return meGusta;
    }

    public void setMeGusta(boolean meGusta) {
        this.meGusta = meGusta;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

}