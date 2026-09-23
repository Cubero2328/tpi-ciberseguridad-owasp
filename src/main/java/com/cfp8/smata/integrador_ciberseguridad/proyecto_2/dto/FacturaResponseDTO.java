package com.cfp8.smata.integrador_ciberseguridad.proyecto_2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FacturaResponseDTO {
    private Long id;
    private String propietario;
    private String detalle;
    private Double monto;
    private String fecha;
}

