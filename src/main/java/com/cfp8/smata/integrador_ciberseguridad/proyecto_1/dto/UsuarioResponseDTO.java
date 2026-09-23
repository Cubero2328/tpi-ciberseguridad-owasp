package com.cfp8.smata.integrador_ciberseguridad.proyecto_1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String rol;
}
