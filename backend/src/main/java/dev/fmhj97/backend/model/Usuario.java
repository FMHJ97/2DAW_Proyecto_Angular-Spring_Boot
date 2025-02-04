package dev.fmhj97.backend.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    @Column(name = "usuario", unique = true, nullable = false)
    private String usuario;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "pais", nullable = false)
    private String pais;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "rol", nullable = false)
    private String rol;

    @Column(name = "sexo", nullable = false)
    private String sexo;

    /**
     * Constructor vacío de la clase Usuario.
     */
    public Usuario() {
    }

    /**
     * Constructor con parámetros de la clase Usuario.
     */
    public Usuario(int id, String usuario, String email, Date fechaNacimiento, String fullName, String pais,
            String password, String rol, String sexo) {
        this.id = id;
        this.usuario = usuario;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.fullName = fullName;
        this.pais = pais;
        this.password = password;
        this.rol = rol;
        this.sexo = sexo;
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
}
