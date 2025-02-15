package dev.fmhj97.backend.controller;

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

import dev.fmhj97.backend.model.RelatoGenero;
import dev.fmhj97.backend.repository.GeneroRepository;
import dev.fmhj97.backend.repository.RelatoGeneroRepository;
import dev.fmhj97.backend.repository.RelatoRepository;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/relato-generos")
public class RelatoGeneroController {

    @Autowired
    RelatoGeneroRepository relatoGeneroRep;
    @Autowired
    RelatoRepository relatoRep;
    @Autowired
    GeneroRepository generoRep;

    // Obtener todas las relaciones relato-genero
    @GetMapping("/all")
    public List<DTO> getRelatoGeneros() {
        List<DTO> relatoGeneroListDTO = new ArrayList<>();
        List<RelatoGenero> relatoGeneros = relatoGeneroRep.findAll();
        for (RelatoGenero rg : relatoGeneros) {
            DTO relatoGeneroDTO = new DTO();
            relatoGeneroDTO.put("id", rg.getId());
            relatoGeneroDTO.put("id_relato", rg.getRelato().getId());
            relatoGeneroDTO.put("id_genero", rg.getGenero().getId());
            relatoGeneroListDTO.add(relatoGeneroDTO);
        }
        return relatoGeneroListDTO;
    }

    // Obtener una relación relato-genero por ID
    @PostMapping(path = "/get", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO getRelatoGenero(@RequestBody DTO soloid, HttpServletRequest request) {
        DTO dto = new DTO();
        RelatoGenero rg = relatoGeneroRep.findById(Integer.parseInt(soloid.get("id").toString()));
        if (rg != null) {
            dto.put("id", rg.getId());
            dto.put("id_relato", rg.getRelato().getId());
            dto.put("id_genero", rg.getGenero().getId());
        } else {
            dto.put("result", "fail");
        }
        return dto;
    }

    // Crear una nueva relación relato-genero
    @PostMapping(path = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO newRelatoGenero(@RequestBody RelatoGeneroRegisterData rg, HttpServletRequest request) {
        DTO dto = new DTO();
        // Crear la relación relato-genero y guardarla en la base de datos
        RelatoGenero relatoGenero = new RelatoGenero();
        relatoGenero.setRelato(relatoRep.findById(rg.id_relato));
        relatoGenero.setGenero(generoRep.findById(rg.id_genero));
        // Guardar la relación relato-genero en la base de datos
        relatoGeneroRep.save(relatoGenero);
        dto.put("result", "ok");
        dto.put("msg", "Relato-Genero creado");
        return dto;
    }

    // Editar una relación relato-genero
    @PutMapping(path = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO editRelatoGenero(@RequestBody RelatoGeneroRegisterData rg, HttpServletRequest request) {
        DTO dto = new DTO();
        RelatoGenero relatoGenero = relatoGeneroRep.findById(rg.id);
        if (relatoGenero != null) {
            relatoGenero.setRelato(relatoRep.findById(rg.id_relato));
            relatoGenero.setGenero(generoRep.findById(rg.id_genero));

            relatoGeneroRep.save(relatoGenero);
            dto.put("result", "ok");
            dto.put("msg", "Relato-Genero actualizado");
        } else {
            dto.put("result", "fail");
        }
        return dto;
    }

    // Eliminar una relación relato-genero por ID
    @DeleteMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO deleteRelatoGenero(@RequestBody DTO soloid, HttpServletRequest request) {
        DTO dto = new DTO();
        RelatoGenero rg = relatoGeneroRep.findById(Integer.parseInt(soloid.get("id").toString()));
        if (rg != null) {
            relatoGeneroRep.delete(rg);
            dto.put("result", "ok");
            dto.put("msg", "Relato-Genero eliminado");
        } else {
            dto.put("result", "fail");
        }
        return dto;
    }

    // Clases internas para manejar datos de entrada
    static class RelatoGeneroRegisterData {
        int id;
        int id_relato;
        int id_genero;

        public RelatoGeneroRegisterData(int id, int id_relato, int id_genero) {
            this.id = id;
            this.id_relato = id_relato;
            this.id_genero = id_genero;
        }
    }
}