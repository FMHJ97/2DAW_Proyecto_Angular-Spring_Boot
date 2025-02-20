package dev.fmhj97.backend.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.servlet.http.Cookie;
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

import dev.fmhj97.backend.jwt.AutenticadorJWT;
import dev.fmhj97.backend.model.Usuario;
import dev.fmhj97.backend.repository.UsuarioRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:4200")
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

    // Autenticar un usuario
    @PostMapping(path = "/auth", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO autenticaUsuario(@RequestBody UsuarioAuthData uad, HttpServletRequest request,
            HttpServletResponse response) {
        DTO dto = new DTO();
        // Buscar el usuario por nombre de usuario.
        Usuario autenticado = usuarioRep.findByUsuario(uad.usuario);
        // Si se encuentra el usuario, comprobar la contraseña.
        if (autenticado != null) {
            // Comprobar la contraseña con la contraseña encriptada en la base de datos.
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(uad.password, autenticado.getPassword())) {
                // Si la contraseña es correcta, generar un token JWT.
                String token = AutenticadorJWT.codificaJWT(autenticado);
                // Devolver un DTO con resultado "ok".
                dto.put("result", "ok");
                dto.put("jwt", token);
                // Añadir el token a una cookie.
                Cookie cookie = new Cookie("jwt", token);
                cookie.setMaxAge(-1); // La cookie se elimina al cerrar el navegador.
                // cookie.setPath("/"); // La cookie es válida para todo el dominio.
                // cookie.setHttpOnly(true); // La cookie no es accesible desde JavaScript.
                // cookie.setSecure(false); // La cookie no requiere conexión segura (SSL).
                response.addCookie(cookie);
            } else {
                // Si la contraseña no es correcta, devolver un DTO con resultado "fail".
                dto.put("result", "fail");
                dto.put("msg", "Contraseña incorrecta");
            }
        } else {
            // Si no se encuentra el usuario, devolver un DTO con resultado "fail".
            dto.put("result", "fail");
            dto.put("msg", "Usuario no encontrado");
        }

        return dto;
    }

    @GetMapping(path = "/who")
    public DTO getAuth(HttpServletRequest request) {
        DTO dto = new DTO();
        dto.put("result", "fail");

        // Obtener el token desde el header Authorization
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            dto.put("msg", "No se ha enviado el token JWT en el header");
            return dto;
        }

        // Extraer el token eliminando "Bearer "
        String token = authHeader.substring(7);

        // Obtener el ID del usuario desde el token
        int authUsuario = AutenticadorJWT.getIdUsuarioDesdeJWT(token);
        if (authUsuario == -1) {
            dto.put("msg", "Token inválido o usuario no autenticado");
            return dto;
        }

        // Buscar el usuario en la base de datos
        Usuario u = usuarioRep.findById(authUsuario);
        if (u != null) {
            dto.put("id", u.getId());
            dto.put("nombre", u.getNombre());
            dto.put("apellidos", u.getApellidos());
            dto.put("usuario", u.getUsuario());
            dto.put("email", u.getEmail());
            dto.put("fecha_nacimiento", u.getFechaNacimiento().toString());
            dto.put("pais", u.getPais());
            dto.put("sexo", u.getSexo());
            dto.put("rol", u.getRol());
            dto.put("result", "ok");
            dto.put("msg", "Usuario autenticado");
        } else {
            dto.put("msg", "Usuario no encontrado");
        }

        return dto;
    }

    // Obtener datos del usuario autenticado desde el token JWT.
    @GetMapping(path = "/who2")
    public DTO getAuth2(HttpServletRequest request) {
        // Crear un DTO para devolver los datos del usuario autenticado.
        DTO dto = new DTO();
        // Por defecto, el resultado es "fail".
        dto.put("result", "fail");
        // Obtenemos las cookies de la petición.
        Cookie[] c = request.getCookies();
        // Bandera para indicar si se ha encontrado el token JWT.
        int authUsuario = -1;
        // Iteramos sobre las cookies para buscar el token JWT.
        for (Cookie cookie : c) {
            // Si encontramos la cookie con nombre "jwt", obtenemos el ID de usuario.
            if (cookie.getName().equals("jwt")) {
                // Obtenemos el ID de usuario desde el token JWT.
                authUsuario = AutenticadorJWT.getIdUsuarioDesdeJWT(cookie.getValue());
            }
        }
        // Si se ha encontrado el token JWT, obtenemos los datos del usuario.
        Usuario u = usuarioRep.findById(authUsuario);
        if (u != null) {
            // Creamos un DTO con los datos del usuario.
            dto.put("id", u.getId());
            dto.put("nombre", u.getNombre());
            dto.put("apellidos", u.getApellidos());
            dto.put("usuario", u.getUsuario());
            dto.put("email", u.getEmail());
            dto.put("fecha_nacimiento", u.getFechaNacimiento().toString());
            dto.put("pais", u.getPais());
            dto.put("sexo", u.getSexo());
            dto.put("rol", u.getRol());
            // Cambiamos el resultado a "ok".
            dto.put("result", "ok");
            dto.put("msg", "Usuario autenticado");
        } else {
            // Si no se encuentra el usuario, devolvemos un mensaje de error.
            dto.put("msg", "Usuario no encontrado");
        }

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