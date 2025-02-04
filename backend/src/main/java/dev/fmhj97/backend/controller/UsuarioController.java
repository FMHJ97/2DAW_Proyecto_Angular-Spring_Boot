package dev.fmhj97.backend.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.fmhj97.backend.model.Usuario;
import dev.fmhj97.backend.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuRep;

    /**
     * Clase DatosAltaUsuario.
     */
    static class DatosAltaUsuario {
        public int id;
        public String usuario;
        public String password;
        public String fullName;
        public String sexo;
        public Date fechaNacimiento;
        public String pais;
        public String email;
        public String rol;

        /**
         * Constructor con parámetros de la clase DatosAltaUsuario.
         * 
         * @param id
         * @param usuario
         * @param password
         * @param fullName
         * @param sexo
         * @param fechaNacimiento
         * @param pais
         * @param email
         * @param rol
         */
        public DatosAltaUsuario(int id, String usuario, String password, String fullName, String sexo,
                Date fechaNacimiento, String pais, String email, String rol) {
            this.id = id;
            this.usuario = usuario;
            this.password = password;
            this.fullName = fullName;
            this.sexo = sexo;
            this.fechaNacimiento = fechaNacimiento;
            this.pais = pais;
            this.email = email;
            this.rol = rol;
        }

    }

    @DeleteMapping(path = "/delete1", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO deleteUsuario(@RequestBody DTO soloId, HttpServletRequest request) {
        // Creamos un DTO para el usuario.
        DTO dto_u = new DTO();
        // Obtenemos el id del usuario.
        int id = Integer.parseInt(soloId.get("id").toString());
        // Buscamos el usuario por id.
        Usuario u = usuRep.findById(id);
        // Si el usuario existe.
        if (u != null) {
            // Eliminamos el usuario.
            usuRep.delete(u);
            // Asignamos un mensaje de éxito al DTO.
            dto_u.put("success", "Usuario eliminado.");
        } else {
            // Si el usuario no existe.
            dto_u.put("error", "Usuario no encontrado.");
        }
        // Retornamos el DTO.
        return dto_u;
    }

    @PostMapping(path = "/add1", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUsuario(@RequestBody DatosAltaUsuario dau, HttpServletRequest request) {
        // Creamos un nuevo usuario.
        Usuario u = new Usuario(dau.id, dau.usuario, dau.email, dau.fechaNacimiento, dau.fullName, dau.pais,
                dau.password,
                dau.rol, dau.sexo);
        // Guardamos el usuario en la base de datos.
        usuRep.save(u);
    }

    @PostMapping(path = "/get1", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO getUsuario(@RequestBody DTO soloId, HttpServletRequest request) {
        // Creamos un DTO para el usuario.
        DTO dto_u = new DTO();
        // Obtenemos el id del usuario.
        int id = Integer.parseInt(soloId.get("id").toString());
        // Buscamos el usuario por id.
        Usuario u = usuRep.findById(id);
        // Si el usuario existe.
        if (u != null) {
            // Asignamos los valores del usuario al DTO.
            dto_u.put("id", u.getId());
            dto_u.put("usuario", u.getUsuario());
            dto_u.put("password", u.getPassword());
            dto_u.put("fullName", u.getFullName());
            dto_u.put("sexo", u.getSexo());
            dto_u.put("fechaNacimiento", u.getFechaNacimiento().toString());
            dto_u.put("pais", u.getPais());
            dto_u.put("email", u.getEmail());
            dto_u.put("rol", u.getRol());
        } else {
            // Si el usuario no existe.
            dto_u.put("error", "Usuario no encontrado.");
        }
        // Retornamos el DTO.
        return dto_u;
    }

    @GetMapping(path = "/all")
    public List<DTO> getUsuarios() {
        // Inicializamos la lista de DTO.
        List<DTO> dto_list = new ArrayList<DTO>();
        // Obtenemos la lista de usuarios.
        List<Usuario> user_list = usuRep.findAll();
        // Recorremos la lista de usuarios.
        for (Usuario u : user_list) {
            // Creamos un DTO para cada usuario.
            DTO dto_u = new DTO();
            // Asignamos los valores del usuario al DTO.
            dto_u.put("id", u.getId());
            dto_u.put("usuario", u.getUsuario());
            dto_u.put("password", u.getPassword());
            dto_u.put("fullName", u.getFullName());
            dto_u.put("sexo", u.getSexo());
            dto_u.put("fechaNacimiento", u.getFechaNacimiento().toString());
            dto_u.put("pais", u.getPais());
            dto_u.put("email", u.getEmail());
            dto_u.put("rol", u.getRol());
            // Agregamos el DTO a la lista de DTO.
            dto_list.add(dto_u);
        }
        // Retornamos la lista de DTO.
        return dto_list;
    }

}
