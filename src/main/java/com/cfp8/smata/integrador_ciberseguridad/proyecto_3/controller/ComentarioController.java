package com.cfp8.smata.integrador_ciberseguridad.proyecto_3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cfp8.smata.integrador_ciberseguridad.proyecto_3.dto.ComentarioDTO;
import com.cfp8.smata.integrador_ciberseguridad.proyecto_3.service.ComentarioService;

import java.util.List;

@RestController
@RequestMapping("/api/proyecto3")
@CrossOrigin(origins = "*")
public class ComentarioController {

    @Autowired
    private ComentarioService service;

    // Obtener los comentarios del tablón
    @GetMapping("/comentarios")
    public ResponseEntity<List<ComentarioDTO>> obtenerComentarios() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // Guardar usando el canal vulnerable
    @PostMapping("/guardar-vulnerable")
    public ResponseEntity<ComentarioDTO> publicarVulnerable(@RequestBody ComentarioDTO dto) {
        return ResponseEntity.ok(service.guardarVulnerable(dto));
    }

    // Guardar usando el canal mitigado
    @PostMapping("/guardar-mitigado")
    public ResponseEntity<ComentarioDTO> publicarMitigado(@RequestBody ComentarioDTO dto) {
        return ResponseEntity.ok(service.guardarMitigado(dto));
    }
}
