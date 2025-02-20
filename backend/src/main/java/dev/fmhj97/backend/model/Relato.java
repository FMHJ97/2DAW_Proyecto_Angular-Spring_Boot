package dev.fmhj97.backend.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "relato")
public class Relato implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(nullable = false, length = 255)
    private String resumen;

    @Column(nullable = false)
    private String contenido;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_publicacion", nullable = false)
    private LocalDateTime fechaPublicacion;

    @Column(name = "portada_url", length = 255)
    private String portadaUrl;

    // Relación con RelatoGenero
    @OneToMany(mappedBy = "relato")
    private List<RelatoGenero> relatoGeneros;

    // Relación con RelatoUsuario
    @OneToMany(mappedBy = "relato")
    private List<RelatoUsuario> relatoUsuarios;

    // Constructores
    public Relato() {
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getPortadaUrl() {
        return portadaUrl;
    }

    public void setPortadaUrl(String portadaUrl) {
        this.portadaUrl = portadaUrl;
    }

    // Métodos para manejar la relación con RelatoGenero
    public List<RelatoGenero> getRelatoGeneros() {
        return relatoGeneros;
    }

    public void setRelatoGeneros(List<RelatoGenero> relatoGeneros) {
        this.relatoGeneros = relatoGeneros;
    }

    public RelatoGenero addRelatoGenero(RelatoGenero relatoGenero) {
        getRelatoGeneros().add(relatoGenero);
        relatoGenero.setRelato(this);
        return relatoGenero;
    }

    public RelatoGenero removeRelatoGenero(RelatoGenero relatoGenero) {
        getRelatoGeneros().remove(relatoGenero);
        relatoGenero.setRelato(null);
        return relatoGenero;
    }

    // Métodos para manejar la relación con RelatoUsuario
    public List<RelatoUsuario> getRelatoUsuarios() {
        return relatoUsuarios;
    }

    public void setRelatoUsuarios(List<RelatoUsuario> relatoUsuarios) {
        this.relatoUsuarios = relatoUsuarios;
    }

    public RelatoUsuario addRelatoUsuario(RelatoUsuario relatoUsuario) {
        getRelatoUsuarios().add(relatoUsuario);
        relatoUsuario.setRelato(this);
        return relatoUsuario;
    }

    public RelatoUsuario removeRelatoUsuario(RelatoUsuario relatoUsuario) {
        getRelatoUsuarios().remove(relatoUsuario);
        relatoUsuario.setRelato(null);
        return relatoUsuario;
    }

}