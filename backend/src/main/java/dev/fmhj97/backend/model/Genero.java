package dev.fmhj97.backend.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the genero database table.
 * 
 */
@Entity
@NamedQuery(name="Genero.findAll", query="SELECT g FROM Genero g")
public class Genero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String nombre;

	//bi-directional many-to-one association to GeneroObra
	@OneToMany(mappedBy="genero")
	private List<GeneroObra> generoObras;

	public Genero() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<GeneroObra> getGeneroObras() {
		return this.generoObras;
	}

	public void setGeneroObras(List<GeneroObra> generoObras) {
		this.generoObras = generoObras;
	}

	public GeneroObra addGeneroObra(GeneroObra generoObra) {
		getGeneroObras().add(generoObra);
		generoObra.setGenero(this);

		return generoObra;
	}

	public GeneroObra removeGeneroObra(GeneroObra generoObra) {
		getGeneroObras().remove(generoObra);
		generoObra.setGenero(null);

		return generoObra;
	}

}