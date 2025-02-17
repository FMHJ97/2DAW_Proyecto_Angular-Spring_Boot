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

import dev.fmhj97.backend.model.Genero;
import dev.fmhj97.backend.repository.GeneroRepository;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:4200")
@RestController // Indica que esta clase es un controlador REST (API).
@RequestMapping("/generos") // Ruta base para todos los endpoints de este controlador.
public class GeneroController {

    // Repositorio de generos.
    @Autowired
    GeneroRepository generoRep;

    // Obtener todos los generos
    @GetMapping("/all")
    public List<DTO> getGeneros() {
        // Crear una lista de DTOs para devolver los generos.
        List<DTO> generoListDTO = new ArrayList<>();
        // Obtener todos los generos de la base de datos.
        List<Genero> generos = generoRep.findAll();
        // Iterar sobre la lista de generos y crear un DTO para cada uno.
        for (Genero g : generos) {
            DTO generoDTO = new DTO();
            generoDTO.put("id", g.getId());
            generoDTO.put("nombre", g.getNombre());
            // Agregar el DTO a la lista de DTOs.
            generoListDTO.add(generoDTO);
        }
        // Devolver la lista de DTOs.
        return generoListDTO;
    }

    // Obtener un genero por ID
    @PostMapping(path = "/get", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO getGenero(@RequestBody DTO soloid, HttpServletRequest request) {
        // Crear un DTO para devolver el resultado.
        DTO dto = new DTO();
        // Buscar el genero por ID.
        Genero g = generoRep.findById(Integer.parseInt(soloid.get("id").toString()));
        // Si se encuentra el genero, devolver un DTO con los datos del genero.
        if (g != null) {
            dto.put("id", g.getId());
            dto.put("nombre", g.getNombre());
        } else {
            // Si no se encuentra el genero, devolver un DTO con resultado "fail".
            dto.put("result", "fail");
        }
        // Devolver el DTO.
        return dto;
    }

    // Crear un nuevo genero
    @PostMapping(path = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO newGenero(@RequestBody GeneroRegisterData g, HttpServletRequest request) {
        // Crear un DTO para devolver el resultado.
        DTO dto = new DTO();
        // Crear un nuevo genero con los datos recibidos.
        Genero genero = new Genero();
        genero.setNombre(g.nombre);

        // Guardar el nuevo genero en la base de datos.
        generoRep.save(genero);
        // Devolver un DTO con resultado "ok".
        dto.put("result", "ok");
        dto.put("msg", "Genero creado");
        // Devolver el DTO.
        return dto;
    }

    // Editar un genero
    @PutMapping(path = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO editGenero(@RequestBody GeneroRegisterData g, HttpServletRequest request) {
        // Crear un DTO para devolver el resultado.
        DTO dto = new DTO();
        // Buscar el genero por ID.
        Genero genero = generoRep.findById(g.id);
        // Si se encuentra el genero, actualizar los datos y guardar en la base de
        // datos.
        if (genero != null) {
            genero.setNombre(g.nombre);
            generoRep.save(genero);
            // Devolver un DTO con resultado "ok".
            dto.put("result", "ok");
            dto.put("msg", "Genero actualizado");
        } else {
            // Si no se encuentra el genero, devolver un DTO con resultado "fail".
            dto.put("result", "fail");
        }
        // Devolver el DTO.
        return dto;
    }

    // Eliminar un genero por ID
    @DeleteMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DTO deleteGenero(@RequestBody DTO soloid, HttpServletRequest request) {
        // Crear un DTO para devolver el resultado.
        DTO dto = new DTO();
        // Buscar el genero por ID.
        Genero g = generoRep.findById(Integer.parseInt(soloid.get("id").toString()));
        // Si se encuentra el genero, eliminarlo de la base de datos.
        if (g != null) {
            generoRep.delete(g);
            // Devolver un DTO con resultado "ok".
            dto.put("result", "ok");
            dto.put("msg", "Genero eliminado");
        } else {
            // Si no se encuentra el genero, devolver un DTO con resultado "fail".
            dto.put("result", "fail");
        }
        // Devolver el DTO.
        return dto;
    }

    // Clases internas para manejar datos de entrada

    // Clase para manejar los datos de registro de un genero.
    static class GeneroRegisterData {
        int id;
        String nombre;

        public GeneroRegisterData(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }
    }
}