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

import dev.fmhj97.backend.model.Comentario;
import dev.fmhj97.backend.repository.ComentarioRepository;
import dev.fmhj97.backend.repository.RelatoRepository;
import dev.fmhj97.backend.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    ComentarioRepository comentarioRep;
    @Autowired
    UsuarioRepository usuarioRep;
    @Autowired
    RelatoRepository relatoRep;

    // Obtener todos los comentarios
    @GetMapping("/all")
    public List<DTO> getComentarios() {
        List<DTO> comentarioListDTO = new ArrayList<>();
        List<Comentario> comentarios = comentarioRep.findAll();
        for (Comentario c : comentarios) {
            DTO comentarioDTO = new DTO();
            comentarioDTO.put("id", c.getId());
            comentarioDTO.put("contenido", c.getContenido());
            comentarioDTO.put("fecha_creacion", c.getFechaCreacion().toString());
            comentarioDTO.put("id_usuario", c.getUsuario().getId());
            comentarioDTO.put("id_relato", c.getRelato().getId());
            comentarioListDTO.add(comentarioDTO);
        }
        return comentarioListDTO;
    }

    // Obtener un comentario por ID
    @PostMapping(path = "/get", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO getComentario(@RequestBody DTO soloid, HttpServletRequest request) {
        DTO dto = new DTO();
        Comentario c = comentarioRep.findById(Integer.parseInt(soloid.get("id").toString()));
        if (c != null) {
            dto.put("id", c.getId());
            dto.put("contenido", c.getContenido());
            dto.put("fecha_creacion", c.getFechaCreacion().toString());
            dto.put("id_usuario", c.getUsuario().getId());
            dto.put("id_relato", c.getRelato().getId());
        } else {
            dto.put("result", "fail");
        }
        return dto;
    }

    // Crear un nuevo comentario
    @PostMapping(path = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO newComentario(@RequestBody ComentarioRegisterData c, HttpServletRequest request) {
        DTO dto = new DTO();
        // Creamos un nuevo comentario
        Comentario comentario = new Comentario();
        comentario.setContenido(c.contenido);
        // Le pasamos la fecha actual al crear el comentario.
        comentario.setFechaCreacion(LocalDateTime.now());
        comentario.setUsuario(usuarioRep.findById(c.id_usuario));
        comentario.setRelato(relatoRep.findById(c.id_relato));

        // Guardamos el comentario en la base de datos
        comentarioRep.save(comentario);
        dto.put("result", "ok");
        dto.put("msg", "Comentario creado");
        return dto;
    }

    // Editar un comentario
    @PutMapping(path = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO editComentario(@RequestBody ComentarioRegisterData c, HttpServletRequest request) {
        DTO dto = new DTO();
        Comentario comentario = comentarioRep.findById(c.id);
        if (comentario != null) {
            comentario.setContenido(c.contenido);
            // Le pasamos la fecha actual al editar el comentario.
            comentario.setFechaCreacion(LocalDateTime.now());
            comentario.setUsuario(usuarioRep.findById(c.id_usuario));
            comentario.setRelato(relatoRep.findById(c.id_relato));

            comentarioRep.save(comentario);
            dto.put("result", "ok");
            dto.put("msg", "Comentario actualizado");
        } else {
            dto.put("result", "fail");
        }
        return dto;
    }

    // Eliminar un comentario por ID
    @DeleteMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO deleteComentario(@RequestBody DTO soloid, HttpServletRequest request) {
        DTO dto = new DTO();
        Comentario c = comentarioRep.findById(Integer.parseInt(soloid.get("id").toString()));
        if (c != null) {
            comentarioRep.delete(c);
            dto.put("result", "ok");
            dto.put("msg", "Comentario eliminado");
        } else {
            dto.put("result", "fail");
        }
        return dto;
    }

    // Clases internas para manejar datos de entrada
    static class ComentarioRegisterData {
        int id;
        String contenido;
        LocalDateTime fechaCreacion;
        int id_usuario;
        int id_relato;

        public ComentarioRegisterData(int id, String contenido, LocalDateTime fechaCreacion, int id_usuario,
                int id_relato) {
            this.id = id;
            this.contenido = contenido;
            this.fechaCreacion = fechaCreacion;
            this.id_usuario = id_usuario;
            this.id_relato = id_relato;
        }
    }
}