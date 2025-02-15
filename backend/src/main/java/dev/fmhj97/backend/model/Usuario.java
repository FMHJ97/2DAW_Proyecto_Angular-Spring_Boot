package dev.fmhj97.backend.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuario")
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 100)
	private String nombre;

	@Column(nullable = false, length = 100)
	private String apellidos;

	@Column(nullable = false)
	private String sexo;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_nacimiento", nullable = false)
	private Date fechaNacimiento;

	@Column(nullable = false)
	private String pais;

	@Column(nullable = false, length = 50, unique = true)
	private String usuario;

	@Column(nullable = false, length = 255)
	private String password;

	@Column(nullable = false, length = 320, unique = true)
	private String email;

	@Column(nullable = false)
	private String rol;

	@OneToMany(mappedBy = "usuario")
	private List<Comentario> comentarios;

	@OneToMany(mappedBy = "usuario")
	private List<Relato> relatos;

	@OneToMany(mappedBy = "usuario")
	private List<RelatoUsuario> relatoUsuarios;

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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public List<Relato> getRelatos() {
		return relatos;
	}

	public void setRelatos(List<Relato> relatos) {
		this.relatos = relatos;
	}

	public List<RelatoUsuario> getRelatoUsuarios() {
		return relatoUsuarios;
	}

	public void setRelatoUsuarios(List<RelatoUsuario> relatoUsuarios) {
		this.relatoUsuarios = relatoUsuarios;
	}

}