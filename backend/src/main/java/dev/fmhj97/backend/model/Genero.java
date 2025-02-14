package dev.fmhj97.backend.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "genero")
@NamedQuery(name = "Genero.findAll", query = "SELECT g FROM Genero g")
public class Genero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 50)
	private String nombre;

	// Constructores
	public Genero() {
	}

	// Getters y Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}