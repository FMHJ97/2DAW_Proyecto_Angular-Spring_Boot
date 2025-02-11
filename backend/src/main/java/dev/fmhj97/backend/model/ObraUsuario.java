package dev.fmhj97.backend.model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the obra_usuario database table.
 * 
 */
@Entity
@Table(name="obra_usuario")
@NamedQuery(name="ObraUsuario.findAll", query="SELECT o FROM ObraUsuario o")
public class ObraUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="estado_lectura")
	private String estadoLectura;

	private byte favorito;

	//bi-directional many-to-one association to Obra
	@ManyToOne
	@JoinColumn(name="id_obra")
	private Obra obra;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public ObraUsuario() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstadoLectura() {
		return this.estadoLectura;
	}

	public void setEstadoLectura(String estadoLectura) {
		this.estadoLectura = estadoLectura;
	}

	public byte getFavorito() {
		return this.favorito;
	}

	public void setFavorito(byte favorito) {
		this.favorito = favorito;
	}

	public Obra getObra() {
		return this.obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}