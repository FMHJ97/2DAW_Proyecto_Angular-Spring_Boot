package dev.fmhj97.backend.model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the genero_obra database table.
 * 
 */
@Entity
@Table(name="genero_obra")
@NamedQuery(name="GeneroObra.findAll", query="SELECT g FROM GeneroObra g")
public class GeneroObra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Genero
	@ManyToOne
	@JoinColumn(name="id_genero")
	private Genero genero;

	//bi-directional many-to-one association to Obra
	@ManyToOne
	@JoinColumn(name="id_obra")
	private Obra obra;

	public GeneroObra() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Genero getGenero() {
		return this.genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Obra getObra() {
		return this.obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

}