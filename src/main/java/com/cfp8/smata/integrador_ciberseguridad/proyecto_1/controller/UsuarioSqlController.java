package com.cfp8.smata.integrador_ciberseguridad.proyecto_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cfp8.smata.integrador_ciberseguridad.proyecto_1.dto.UsuarioResponseDTO;
import com.cfp8.smata.integrador_ciberseguridad.proyecto_1.service.UsuarioSqlService;

import java.util.List;

@RestController
@RequestMapping("/api/proyecto1")
@CrossOrigin(origins = "*") // Permite peticiones desde el front local
public class UsuarioSqlController {

    @Autowired
    private UsuarioSqlService service;

    // Endpoint del laboratorio vulnerable
    @GetMapping("/buscar-vulnerable")
    public List<UsuarioResponseDTO> buscarVulnerable(@RequestParam String username) {
        return service.buscarVulnerable(username);
    }

    // Endpoint del laboratorio mitigado
    @GetMapping("/buscar-mitigado")
    public List<UsuarioResponseDTO> buscarMitigado(@RequestParam String username) {
        return service.buscarMitigado(username);
    }
}
