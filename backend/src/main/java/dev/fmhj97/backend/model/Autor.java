package dev.fmhj97.backend.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the autor database table.
 * 
 */
@Entity
@NamedQuery(name="Autor.findAll", query="SELECT a FROM Autor a")
public class Autor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String biografia;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_fallecimiento")
	private Date fechaFallecimiento;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;

	@Column(name="full_name")
	private String fullName;

	private String imagen;

	private String pais;

	//bi-directional many-to-one association to Obra
	@OneToMany(mappedBy="autor")
	private List<Obra> obras;

	public Autor() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBiografia() {
		return this.biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public Date getFechaFallecimiento() {
		return this.fechaFallecimiento;
	}

	public void setFechaFallecimiento(Date fechaFallecimiento) {
		this.fechaFallecimiento = fechaFallecimiento;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public List<Obra> getObras() {
		return this.obras;
	}

	public void setObras(List<Obra> obras) {
		this.obras = obras;
	}

	public Obra addObra(Obra obra) {
		getObras().add(obra);
		obra.setAutor(this);

		return obra;
	}

	public Obra removeObra(Obra obra) {
		getObras().remove(obra);
		obra.setAutor(null);

		return obra;
	}

}