package com.cfp8.smata.integrador_ciberseguridad.proyecto_3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioDTO {
    private Long id;
    private String autor;
    private String contenido;
    private String fecha;
}
