package com.cfp8.smata.integrador_ciberseguridad.proyecto_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cfp8.smata.integrador_ciberseguridad.proyecto_2.service.FacturaService;

@RestController
@RequestMapping("/api/proyecto2")
@CrossOrigin(origins = "*")
public class FacturaController {

    @Autowired
    private FacturaService service;

    /**
     * Endpoint Vulnerable: Cualquiera puede pasar el ID que quiera por la URL
     * e ignorar por completo quién está logueado.
     */
    @GetMapping("/factura-vulnerable/{id}")
    public ResponseEntity<?> verFacturaVulnerable(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.obtenerFacturaVulnerable(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint Mitigado: Exige saber quién es el usuario logueado 
     * y el servicio valida los permisos sobre el ID solicitado.
     */
    @GetMapping("/factura-mitigado/{id}")
    public ResponseEntity<?> verFacturaMitigado(
            @PathVariable Long id, 
            @RequestParam String usuarioLogueado) {
        try {
            return ResponseEntity.ok(service.obtenerFacturaMitigado(id, usuarioLogueado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
