package com.cfp8.smata.integrador_ciberseguridad.proyecto_2.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfp8.smata.integrador_ciberseguridad.proyecto_2.dto.FacturaResponseDTO;
import com.cfp8.smata.integrador_ciberseguridad.proyecto_2.model.Factura;
import com.cfp8.smata.integrador_ciberseguridad.proyecto_2.repository.FacturaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository repository;

    // Cargamos facturas de prueba de diferentes usuarios
    @PostConstruct
    public void cargarDatosPrueba() {
        if (repository.count() == 0) {
            repository.save(new Factura(null, "juan99", "Servicio de Internet - Enero", 4500.0, "2026-01-10"));
            repository.save(new Factura(null, "juan99", "Factura Luz - Enero", 8200.0, "2026-01-15"));
            repository.save(new Factura(null, "ana_security", "Compra Laptop Corporativa", 850000.0, "2026-02-01"));
            repository.save(new Factura(null, "admin", "Gastos de Servidores Cloud", 125000.0, "2026-02-05"));
        }
    }

    /**
     * VULNERABLE: Retorna cualquier factura basada solo en su ID.
     * No verifica quién está pidiendo la información.
     */
    public FacturaResponseDTO obtenerFacturaVulnerable(Long idFactura) {
        Factura factura = repository.findById(idFactura)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
        
        return new FacturaResponseDTO(factura.getId(), factura.getPropietario(), factura.getDetalle(), factura.getMonto(), factura.getFecha());
    }

    /**
     * MITIGADO: Valida explícitamente si el usuario que hace el request 
     * es el legítimo propietario de la factura consultada.
     */
    public FacturaResponseDTO obtenerFacturaMitigado(Long idFactura, String usuarioAutenticado) {
        Factura factura = repository.findById(idFactura)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));

        // Regla de Control de Acceso: Si no es el dueño y tampoco es administrador, se bloquea
        if (!factura.getPropietario().equals(usuarioAutenticado) && !usuarioAutenticado.equals("admin")) {
            throw new RuntimeException("ACCESO DENEGADO: No tienes permisos para ver este recurso.");
        }

        return new FacturaResponseDTO(factura.getId(), factura.getPropietario(), factura.getDetalle(), factura.getMonto(), factura.getFecha());
    }
}
