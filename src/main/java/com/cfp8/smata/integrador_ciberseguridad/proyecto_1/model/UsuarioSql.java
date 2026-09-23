package com.cfp8.smata.integrador_ciberseguridad.proyecto_1.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios_sql")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSql {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String username;
    
    private String email;
    
    // Almacenamos texto plano SOLO con fines didácticos para la demo del ataque
    private String password; 
    
    private String rol;
}
