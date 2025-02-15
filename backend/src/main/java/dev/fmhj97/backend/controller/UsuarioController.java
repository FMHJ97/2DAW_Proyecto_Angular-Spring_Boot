package dev.fmhj97.backend.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin // Permite peticiones desde cualquier origen.
@RestController // Indica que esta clase es un controlador REST (API).
@RequestMapping("/usuarios") // Ruta base para todos los endpoints de este controlador.
public class UsuarioController {

    // Repositorio de usuarios.
    @Autowired
    UsuarioRepository usuarioRep;

    // Obtener todos los usuarios
    @GetMapping("/all")
    public List<DTO> getUsuarios() {
        // Crear una lista de DTOs para devolver los usuarios.
        List<DTO> usuarioListDTO = new ArrayList<>();
        // Obtener todos los usuarios de la base de datos.
        List<Usuario> usuarios = usuarioRep.findAll();
        // Iterar sobre la lista de usuarios y crear un DTO para cada uno.
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
            // Agregar el DTO a la lista de DTOs.
            usuarioListDTO.add(usuarioDTO);
        }
        // Devolver la lista de DTOs.
        return usuarioListDTO;
    }

    // Obtener un usuario por ID
    @PostMapping(path = "/get", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO getUsuario(@RequestBody DTO soloid, HttpServletRequest request) {
        // Crear un DTO para devolver el usuario.
        DTO usuarioDTO = new DTO();
        // Buscar el usuario por ID.
        Usuario u = usuarioRep.findById(Integer.parseInt(soloid.get("id").toString()));
        // Si se encuentra el usuario, crear el DTO con los datos.
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
            // Si no se encuentra el usuario, devolver un DTO con resultado "fail".
            usuarioDTO.put("result", "fail");
        }
        // Devolver el DTO.
        return usuarioDTO;
    }

    // Crear un nuevo usuario
    @PostMapping(path = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO addUsuario(@RequestBody UsuarioRegisterData urd, HttpServletRequest request) {
        // Crear un DTO para devolver el resultado.
        DTO dto = new DTO();
        // Vamos a encriptar la contraseña antes de guardarla en la base de datos.
        // Para ello, utilizamos la clase BCryptPasswordEncoder de Spring Security.
        // Creamos un objeto de esta clase (debemos usar la dependencia de Spring
        // Security en el archivo pom.xml).
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // Encriptamos la contraseña con el método encode.
        String passwordEncriptada = encoder.encode(urd.password);

        // Asignar los datos al nuevo usuario.
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(urd.nombre);
        nuevoUsuario.setApellidos(urd.apellidos);
        nuevoUsuario.setUsuario(urd.usuario);
        nuevoUsuario.setPassword(passwordEncriptada);
        nuevoUsuario.setEmail(urd.email);
        nuevoUsuario.setFechaNacimiento(urd.fechaNacimiento);
        nuevoUsuario.setPais(urd.pais);
        nuevoUsuario.setSexo(urd.sexo);
        nuevoUsuario.setRol(urd.rol);

        // Guardar el nuevo usuario en la base de datos.
        usuarioRep.save(nuevoUsuario);
        // Devolver un DTO con resultado "ok".
        dto.put("result", "ok");
        dto.put("msg", "Usuario creado");
        return dto;
    }

    // Editar un usuario existente
    @PutMapping(path = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO editUsuario(@RequestBody UsuarioRegisterData u, HttpServletRequest request) {
        // Crear un DTO para devolver el resultado.
        DTO dto = new DTO();
        // Buscar el usuario por ID.
        Usuario usuario = usuarioRep.findById(u.id);
        // Si se encuentra el usuario, actualizar los datos y guardar en la base de
        // datos.
        if (usuario != null) {
            usuario.setNombre(u.nombre);
            usuario.setApellidos(u.apellidos);
            usuario.setUsuario(u.usuario);

            // Comprobar si se ha enviado una nueva contraseña.
            // En caso negativo, no se actualiza la contraseña.
            if (u.password != null && !u.password.isEmpty()) {
                // Vamos a encriptar la nueva contraseña antes de guardarla en la base de datos.
                // Para ello, utilizamos la clase BCryptPasswordEncoder de Spring Security.
                // Creamos un objeto de esta clase (debemos usar la dependencia de Spring
                // Security en el archivo pom.xml).
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                // Encriptamos la contraseña con el método encode.
                String passwordEncriptada = encoder.encode(u.password);
                // Guardar la contraseña encriptada en el usuario.
                usuario.setPassword(passwordEncriptada);
            }

            usuario.setEmail(u.email);
            usuario.setFechaNacimiento(u.fechaNacimiento);
            usuario.setPais(u.pais);
            usuario.setSexo(u.sexo);
            usuario.setRol(u.rol);
            // Guardar el usuario actualizado en la base de datos.
            usuarioRep.save(usuario);
            // Devolver un DTO con resultado "ok".
            dto.put("result", "ok");
            dto.put("msg", "Usuario actualizado");
        } else {
            // Si no se encuentra el usuario, devolver un DTO con resultado "fail".
            dto.put("result", "fail");
            dto.put("message", "Usuario no encontrado");
        }
        // Devolver el DTO.
        return dto;
    }

    // Eliminar un usuario por ID
    @DeleteMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO deleteUsuario(@RequestBody DTO soloid, HttpServletRequest request) {
        // Crear un DTO para devolver el resultado.
        DTO dto = new DTO();
        // Buscar el usuario por ID.
        Usuario u = usuarioRep.findById(Integer.parseInt(soloid.get("id").toString()));
        // Si se encuentra el usuario, eliminarlo de la base de datos.
        if (u != null) {
            usuarioRep.delete(u);
            // Devolver un DTO con resultado "ok".
            dto.put("result", "ok");
            dto.put("msg", "Usuario eliminado");
        } else {
            // Si no se encuentra el usuario, devolver un DTO con resultado "fail".
            dto.put("result", "fail");
        }
        // Devolver el DTO.
        return dto;
    }

    // Clases internas para manejar datos de entrada

    // Clase interna para manejar datos de autenticación de usuario.
    static class UsuarioAuthData {
        String usuario;
        String password;

        public UsuarioAuthData(String usuario, String password) {
            this.usuario = usuario;
            this.password = password;
        }
    }

    // Clase interna para manejar datos de registro de usuario.
    static class UsuarioRegisterData {
        int id;
        String nombre;
        String apellidos;
        String usuario;
        String password;
        String email;
        Date fechaNacimiento;
        String pais;
        String sexo;
        String rol;

        public UsuarioRegisterData(int id, String nombre, String apellidos, String usuario, String password,
                String email, Date fechaNacimiento, String pais, String sexo, String rol) {
            this.id = id;
            this.nombre = nombre;
            this.apellidos = apellidos;
            this.usuario = usuario;
            this.password = password;
            this.email = email;
            this.fechaNacimiento = fechaNacimiento;
            this.pais = pais;
            this.sexo = sexo;
            this.rol = rol;
        }
    }
}