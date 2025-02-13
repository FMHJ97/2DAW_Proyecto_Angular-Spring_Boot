package dev.fmhj97.backend.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the obra database table.
 * 
 */
@Entity
@NamedQuery(name="Obra.findAll", query="SELECT o FROM Obra o")
public class Obra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="contenido_url")
	private String contenidoUrl;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_publicacion")
	private Date fechaPublicacion;

	@Column(name="portada_url")
	private String portadaUrl;

	@Lob
	private String resumen;

	private String titulo;

	//bi-directional many-to-one association to GeneroObra
	@OneToMany(mappedBy="obra")
	private List<GeneroObra> generoObras;

	//bi-directional many-to-one association to Autor
	@ManyToOne
	@JoinColumn(name="id_autor")
	private Autor autor;

	//bi-directional many-to-one association to ObraUsuario
	@OneToMany(mappedBy="obra")
	private List<ObraUsuario> obraUsuarios;

	public Obra() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContenidoUrl() {
		return this.contenidoUrl;
	}

	public void setContenidoUrl(String contenidoUrl) {
		this.contenidoUrl = contenidoUrl;
	}

	public Date getFechaPublicacion() {
		return this.fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getPortadaUrl() {
		return this.portadaUrl;
	}

	public void setPortadaUrl(String portadaUrl) {
		this.portadaUrl = portadaUrl;
	}

	public String getResumen() {
		return this.resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<GeneroObra> getGeneroObras() {
		return this.generoObras;
	}

	public void setGeneroObras(List<GeneroObra> generoObras) {
		this.generoObras = generoObras;
	}

	public GeneroObra addGeneroObra(GeneroObra generoObra) {
		getGeneroObras().add(generoObra);
		generoObra.setObra(this);

		return generoObra;
	}

	public GeneroObra removeGeneroObra(GeneroObra generoObra) {
		getGeneroObras().remove(generoObra);
		generoObra.setObra(null);

		return generoObra;
	}

	public Autor getAutor() {
		return this.autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public List<ObraUsuario> getObraUsuarios() {
		return this.obraUsuarios;
	}

	public void setObraUsuarios(List<ObraUsuario> obraUsuarios) {
		this.obraUsuarios = obraUsuarios;
	}

	public ObraUsuario addObraUsuario(ObraUsuario obraUsuario) {
		getObraUsuarios().add(obraUsuario);
		obraUsuario.setObra(this);

		return obraUsuario;
	}

	public ObraUsuario removeObraUsuario(ObraUsuario obraUsuario) {
		getObraUsuarios().remove(obraUsuario);
		obraUsuario.setObra(null);

		return obraUsuario;
	}

}