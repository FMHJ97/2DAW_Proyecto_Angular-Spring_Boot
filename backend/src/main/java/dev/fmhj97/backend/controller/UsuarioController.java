package dev.fmhj97.backend.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.fmhj97.backend.model.Usuario;
import dev.fmhj97.backend.repository.UsuarioRepository;
import dev.fmhj97.backend.jwtSecurity.AutenticadorJWT;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRep;

    // Obtener todos los usuarios
    @GetMapping("/all")
    public List<DTO> getUsuarios() {
        List<DTO> usuarioListDTO = new ArrayList<>();
        List<Usuario> usuarios = usuarioRep.findAll();
        for (Usuario u : usuarios) {
            DTO usuarioDTO = new DTO();
            usuarioDTO.put("id", u.getId());
            usuarioDTO.put("nombre", u.getNombre());
            usuarioDTO.put("apellidos", u.getApellidos());
            usuarioDTO.put("usuario", u.getUsuario());
            usuarioDTO.put("email", u.getEmail());
            usuarioDTO.put("fecha_nacimiento", u.getFechaNacimiento().toString());
            usuarioDTO.put("pais", u.getPais());
            usuarioDTO.put("sexo", u.getSexo());
            usuarioDTO.put("rol", u.getRol());
            usuarioListDTO.add(usuarioDTO);
        }
        return usuarioListDTO;
    }

    // Obtener un usuario por ID
    @PostMapping(path = "/getone", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO getUsuario(@RequestBody DTO soloid, HttpServletRequest request) {
        DTO usuarioDTO = new DTO();
        Usuario u = usuarioRep.findById(Integer.parseInt(soloid.get("id").toString()));
        if (u != null) {
            usuarioDTO.put("id", u.getId());
            usuarioDTO.put("nombre", u.getNombre());
            usuarioDTO.put("apellidos", u.getApellidos());
            usuarioDTO.put("usuario", u.getUsuario());
            usuarioDTO.put("email", u.getEmail());
            usuarioDTO.put("fecha_nacimiento", u.getFechaNacimiento().toString());
            usuarioDTO.put("pais", u.getPais());
            usuarioDTO.put("sexo", u.getSexo());
            usuarioDTO.put("rol", u.getRol());
        } else {
            usuarioDTO.put("result", "fail");
        }
        return usuarioDTO;
    }

    // Crear un nuevo usuario
    @PostMapping(path = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO addUsuario(@RequestBody UsuarioRegisterData u, HttpServletRequest request) {
        DTO dto = new DTO();
        // Hash de la contraseña usando BCrypt
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(u.pass);

        // Crear y guardar el nuevo usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(u.nombre);
        nuevoUsuario.setApellidos(u.apellidos);
        nuevoUsuario.setUsuario(u.usuario);
        nuevoUsuario.setPassword(hashedPassword);
        nuevoUsuario.setEmail(u.email);
        nuevoUsuario.setFechaNacimiento(u.fechaNacimiento);
        nuevoUsuario.setPais(u.pais);
        nuevoUsuario.setSexo(u.sexo);
        nuevoUsuario.setRol(u.rol);

        usuarioRep.save(nuevoUsuario);
        dto.put("result", "ok");
        return dto;
    }

    // Editar un usuario existente
    @PutMapping(path = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO editUsuario(@RequestBody UsuarioRegisterData u, HttpServletRequest request) {
        DTO dto = new DTO();
        Usuario usuario = usuarioRep.findById(u.id);
        if (usuario != null) {
            usuario.setNombre(u.nombre);
            usuario.setApellidos(u.apellidos);
            usuario.setUsuario(u.usuario);
            usuario.setEmail(u.email);
            usuario.setFechaNacimiento(u.fechaNacimiento);
            usuario.setPais(u.pais);
            usuario.setSexo(u.sexo);
            usuario.setRol(u.rol);

            if (u.pass != null && !u.pass.isEmpty()) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String hashedPassword = encoder.encode(u.pass);
                usuario.setPassword(hashedPassword);
            }

            usuarioRep.save(usuario);
            dto.put("result", "ok");
        } else {
            dto.put("result", "fail");
            dto.put("message", "Usuario no encontrado");
        }
        return dto;
    }

    // Eliminar un usuario por ID
    @DeleteMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO deleteUsuario(@RequestBody DTO soloid, HttpServletRequest request) {
        DTO dto = new DTO();
        Usuario u = usuarioRep.findById(Integer.parseInt(soloid.get("id").toString()));
        if (u != null) {
            usuarioRep.delete(u);
            dto.put("result", "ok");
        } else {
            dto.put("result", "fail");
        }
        return dto;
    }

    // Autenticar un usuario
    @PostMapping(path = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO autenticaUsuario(@RequestBody UsuarioAuthenticationData input, HttpServletRequest request,
            HttpServletResponse response) {
        DTO dto = new DTO();
        dto.put("result", "fail");

        // Buscar el usuario por nombre de usuario
        Usuario authenticatedUser = usuarioRep.findByUsuario(input.usuario);
        if (authenticatedUser != null) {
            // Verificar la contraseña usando BCrypt
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(input.pass, authenticatedUser.getPassword())) {
                dto.put("result", "ok");
                dto.put("jwt", AutenticadorJWT.codificaJWT(authenticatedUser));
            }
        }
        return dto;
    }

    // Obtener el usuario autenticado desde el JWT
    @GetMapping(path = "/who")
    public DTO getAuthenticated(HttpServletRequest request) {
        DTO dto = new DTO();
        int idUsuarioAutenticado = AutenticadorJWT.getIdUsuarioDesdeJwtIncrustadoEnRequest(request);
        if (idUsuarioAutenticado != -1) {
            Usuario u = usuarioRep.findById(idUsuarioAutenticado);
            if (u != null) {
                dto.put("result", "ok");
                dto.put("id", u.getId());
                dto.put("nombre", u.getNombre());
                dto.put("apellidos", u.getApellidos());
                dto.put("usuario", u.getUsuario());
                dto.put("email", u.getEmail());
                dto.put("fecha_nacimiento", u.getFechaNacimiento().toString());
                dto.put("pais", u.getPais());
                dto.put("sexo", u.getSexo());
                dto.put("rol", u.getRol());
            } else {
                dto.put("result", "fail");
            }
        } else {
            dto.put("result", "fail");
        }
        return dto;
    }

    // Clases internas para manejar datos de entrada
    static class UsuarioAuthenticationData {
        String usuario;
        String pass;

        public UsuarioAuthenticationData(String usuario, String pass) {
            this.usuario = usuario;
            this.pass = pass;
        }
    }

    static class UsuarioRegisterData {
        int id;
        String nombre;
        String apellidos;
        String usuario;
        String pass;
        String email;
        Date fechaNacimiento;
        String pais;
        String sexo;
        String rol;

        public UsuarioRegisterData(int id, String nombre, String apellidos, String usuario, String pass, String email,
                Date fechaNacimiento, String pais, String sexo, String rol) {
            this.id = id;
            this.nombre = nombre;
            this.apellidos = apellidos;
            this.usuario = usuario;
            this.pass = pass;
            this.email = email;
            this.fechaNacimiento = fechaNacimiento;
            this.pais = pais;
            this.sexo = sexo;
            this.rol = rol;
        }
    }
}