package dev.fmhj97.backend.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@NamedQuery(name = "Obra.findAll", query = "SELECT o FROM Obra o")
public class Obra implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_publicacion", nullable = false)
    private Date fechaPublicacion;

    @Column(name = "genero", nullable = false)
    private String genero;

    @Column(name = "imagen")
    private String imagen;

    @Lob
    @Column(name = "sinopsis")
    private String sinopsis;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "id_autor", nullable = false)
    private Autor autor;

    public Obra() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaPublicacion() {
        return this.fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getImagen() {
        return this.imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getSinopsis() {
        return this.sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return this.autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
