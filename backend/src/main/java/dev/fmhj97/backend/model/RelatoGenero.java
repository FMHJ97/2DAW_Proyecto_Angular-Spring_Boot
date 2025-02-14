package dev.fmhj97.backend.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "relato_genero")
@NamedQuery(name = "RelatoGenero.findAll", query = "SELECT rg FROM RelatoGenero rg")
public class RelatoGenero implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_relato", nullable = false)
    private Relato relato;

    @ManyToOne
    @JoinColumn(name = "id_genero", nullable = false)
    private Genero genero;

    // Constructores
    public RelatoGenero() {
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

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
}