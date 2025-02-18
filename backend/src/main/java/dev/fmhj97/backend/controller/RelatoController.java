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

import dev.fmhj97.backend.model.Relato;
import dev.fmhj97.backend.repository.RelatoRepository;
import dev.fmhj97.backend.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/relatos")
public class RelatoController {

    @Autowired
    RelatoRepository relatoRep;
    @Autowired
    UsuarioRepository usuarioRep;

    // Obtener todos los relatos
    @GetMapping("/all")
    public List<DTO> getRelatos() {
        List<DTO> relatoListDTO = new ArrayList<>();
        List<Relato> relatos = relatoRep.findAll();
        for (Relato r : relatos) {
            DTO relatoDTO = new DTO();
            relatoDTO.put("id", r.getId());
            relatoDTO.put("titulo", r.getTitulo());
            relatoDTO.put("resumen", r.getResumen());
            relatoDTO.put("contenido", r.getContenido());
            relatoDTO.put("fechaPublicacion", r.getFechaPublicacion().toString());
            relatoDTO.put("portada", r.getPortadaUrl());
            relatoDTO.put("autor", r.getUsuario().getUsuario());
            relatoListDTO.add(relatoDTO);
        }
        return relatoListDTO;
    }

    // Obtener un relato por ID
    @PostMapping(path = "/get", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO getRelato(@RequestBody DTO soloid, HttpServletRequest request) {
        DTO dto = new DTO();
        Relato r = relatoRep.findById(Integer.parseInt(soloid.get("id").toString()));
        if (r != null) {
            dto.put("id", r.getId());
            dto.put("titulo", r.getTitulo());
            dto.put("resumen", r.getResumen());
            dto.put("contenido", r.getContenido());
            dto.put("fecha_publicacion", r.getFechaPublicacion().toString());
            dto.put("portada_url", r.getPortadaUrl());
            dto.put("id_usuario", r.getUsuario().getId());
        } else {
            dto.put("result", "fail");
        }
        return dto;
    }

    // Crear un nuevo relato
    @PostMapping(path = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO newRelato(@RequestBody RelatoRegisterData r, HttpServletRequest request) {
        DTO dto = new DTO();
        // Crear un nuevo relato con los datos recibidos.
        Relato relato = new Relato();
        relato.setTitulo(r.titulo);
        relato.setResumen(r.resumen);
        relato.setContenido(r.contenido);
        relato.setFechaPublicacion(r.fechaPublicacion);
        relato.setPortadaUrl(r.portadaUrl);
        relato.setUsuario(usuarioRep.findById(r.id_usuario));

        // Guardar el relato en la base de datos.
        relatoRep.save(relato);
        dto.put("result", "ok");
        dto.put("msg", "Relato creado");
        return dto;
    }

    // Editar un relato
    @PutMapping(path = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO editRelato(@RequestBody RelatoRegisterData r, HttpServletRequest request) {
        DTO dto = new DTO();
        Relato relato = relatoRep.findById(r.id);
        if (relato != null) {
            relato.setTitulo(r.titulo);
            relato.setResumen(r.resumen);
            relato.setContenido(r.contenido);
            relato.setFechaPublicacion(r.fechaPublicacion);
            relato.setPortadaUrl(r.portadaUrl);
            relato.setUsuario(usuarioRep.findById(r.id_usuario));

            relatoRep.save(relato);
            dto.put("result", "ok");
            dto.put("msg", "Relato actualizado");
        } else {
            dto.put("result", "fail");
        }
        return dto;
    }

    // Eliminar un relato por ID
    @DeleteMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO deleteRelato(@RequestBody DTO soloid, HttpServletRequest request) {
        DTO dto = new DTO();
        Relato r = relatoRep.findById(Integer.parseInt(soloid.get("id").toString()));
        if (r != null) {
            relatoRep.delete(r);
            dto.put("result", "ok");
            dto.put("msg", "Relato eliminado");
        } else {
            dto.put("result", "fail");
        }
        return dto;
    }

    // Clases internas para manejar datos de entrada
    static class RelatoRegisterData {
        int id;
        String titulo;
        String resumen;
        String contenido;
        Date fechaPublicacion;
        String portadaUrl;
        int id_usuario;

        public RelatoRegisterData(int id, String titulo, String resumen, String contenido, Date fechaPublicacion,
                String portadaUrl, int id_usuario) {
            this.id = id;
            this.titulo = titulo;
            this.resumen = resumen;
            this.contenido = contenido;
            this.fechaPublicacion = fechaPublicacion;
            this.portadaUrl = portadaUrl;
            this.id_usuario = id_usuario;
        }
    }
}