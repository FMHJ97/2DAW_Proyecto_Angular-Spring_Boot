package dev.fmhj97.backend.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

import dev.fmhj97.backend.model.RelatoUsuario;
import dev.fmhj97.backend.repository.RelatoRepository;
import dev.fmhj97.backend.repository.RelatoUsuarioRepository;
import dev.fmhj97.backend.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/relato-usuarios")
public class RelatoUsuarioController {

    @Autowired
    RelatoUsuarioRepository relatoUsuarioRep;
    @Autowired
    RelatoRepository relatoRep;
    @Autowired
    UsuarioRepository usuarioRep;

    // Obtener todas las relaciones relato-usuario
    @GetMapping("/all")
    public List<DTO> getRelatoUsuarios() {
        List<DTO> relatoUsuarioListDTO = new ArrayList<>();
        List<RelatoUsuario> relatoUsuarios = relatoUsuarioRep.findAll();
        for (RelatoUsuario ru : relatoUsuarios) {
            DTO relatoUsuarioDTO = new DTO();
            relatoUsuarioDTO.put("id", ru.getId());
            relatoUsuarioDTO.put("id_relato", ru.getRelato().getId());
            relatoUsuarioDTO.put("id_usuario", ru.getUsuario().getId());
            relatoUsuarioDTO.put("me_gusta", ru.isMeGusta());
            relatoUsuarioDTO.put("favorito", ru.isFavorito());
            relatoUsuarioListDTO.add(relatoUsuarioDTO);
        }
        return relatoUsuarioListDTO;
    }

    // Obtener una relación relato-usuario por ID
    @PostMapping(path = "/get", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO getRelatoUsuario(@RequestBody DTO soloid, HttpServletRequest request) {
        DTO dto = new DTO();
        RelatoUsuario ru = relatoUsuarioRep.findById(Integer.parseInt(soloid.get("id").toString()));
        if (ru != null) {
            dto.put("id", ru.getId());
            dto.put("id_relato", ru.getRelato().getId());
            dto.put("id_usuario", ru.getUsuario().getId());
            dto.put("me_gusta", ru.isMeGusta());
            dto.put("favorito", ru.isFavorito());
        } else {
            dto.put("result", "fail");
        }
        return dto;
    }

    // Crear una nueva relación relato-usuario
    @PostMapping(path = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO newRelatoUsuario(@RequestBody RelatoUsuarioRegisterData ru, HttpServletRequest request) {
        DTO dto = new DTO();
        // Crear la relación relato-usuario con los datos recibidos y guardarla en la
        // base de datos.
        RelatoUsuario relatoUsuario = new RelatoUsuario();
        relatoUsuario.setRelato(relatoRep.findById(ru.id_relato));
        relatoUsuario.setUsuario(usuarioRep.findById(ru.id_usuario));
        relatoUsuario.setMeGusta(ru.meGusta);
        relatoUsuario.setFavorito(ru.favorito);
        // Guardar la relación relato-usuario en la base de datos.
        relatoUsuarioRep.save(relatoUsuario);
        dto.put("result", "ok");
        dto.put("msg", "Relato-Usuario creado");
        return dto;
    }

    // Editar una relación relato-usuario
    @PutMapping(path = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO editRelatoUsuario(@RequestBody RelatoUsuarioRegisterData ru, HttpServletRequest request) {
        DTO dto = new DTO();
        RelatoUsuario relatoUsuario = relatoUsuarioRep.findById(ru.id);
        if (relatoUsuario != null) {
            relatoUsuario.setRelato(relatoRep.findById(ru.id_relato));
            relatoUsuario.setUsuario(usuarioRep.findById(ru.id_usuario));
            relatoUsuario.setMeGusta(ru.meGusta);
            relatoUsuario.setFavorito(ru.favorito);

            relatoUsuarioRep.save(relatoUsuario);
            dto.put("result", "ok");
            dto.put("msg", "Relato-Usuario actualizado");
        } else {
            dto.put("result", "fail");
        }
        return dto;
    }

    // Eliminar una relación relato-usuario por ID
    @DeleteMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO deleteRelatoUsuario(@RequestBody DTO soloid, HttpServletRequest request) {
        DTO dto = new DTO();
        RelatoUsuario ru = relatoUsuarioRep.findById(Integer.parseInt(soloid.get("id").toString()));
        if (ru != null) {
            relatoUsuarioRep.delete(ru);
            dto.put("result", "ok");
            dto.put("msg", "Relato-Usuario eliminado");
        } else {
            dto.put("result", "fail");
        }
        return dto;
    }

    // Clases internas para manejar datos de entrada
    static class RelatoUsuarioRegisterData {
        int id;
        int id_relato;
        int id_usuario;
        LocalDateTime ultimaLectura;
        boolean meGusta;
        boolean favorito;

        public RelatoUsuarioRegisterData(int id, int id_relato, int id_usuario,
                boolean meGusta,
                boolean favorito) {
            this.id = id;
            this.id_relato = id_relato;
            this.id_usuario = id_usuario;
            this.meGusta = meGusta;
            this.favorito = favorito;
        }
    }
}