package com.cfp8.smata.integrador_ciberseguridad.proyecto_3.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comentarios_xss")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String autor;

    // Aquí se guardará el texto (puede contener el script malicioso en la versión vulnerable)
    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String contenido;
    
    private String fecha;
}
