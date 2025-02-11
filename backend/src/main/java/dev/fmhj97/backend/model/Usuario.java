package dev.fmhj97.backend.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String usuario;

	private String email;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;

	@Column(name="full_name")
	private String fullName;

	private int id;

	private String pais;

	private String password;

	private String rol;

	private String sexo;

	//bi-directional many-to-one association to ObraUsuario
	@OneToMany(mappedBy="usuario")
	private List<ObraUsuario> obraUsuarios;

	public Usuario() {
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public List<ObraUsuario> getObraUsuarios() {
		return this.obraUsuarios;
	}

	public void setObraUsuarios(List<ObraUsuario> obraUsuarios) {
		this.obraUsuarios = obraUsuarios;
	}

	public ObraUsuario addObraUsuario(ObraUsuario obraUsuario) {
		getObraUsuarios().add(obraUsuario);
		obraUsuario.setUsuario(this);

		return obraUsuario;
	}

	public ObraUsuario removeObraUsuario(ObraUsuario obraUsuario) {
		getObraUsuarios().remove(obraUsuario);
		obraUsuario.setUsuario(null);

		return obraUsuario;
	}

}