package com.cfp8.smata.integrador_ciberseguridad.proyecto_2.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "facturas_acceso")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre de usuario al que le pertenece la factura
    @Column(nullable = false)
    private String propietario;

    private String detalle;
    
    private Double monto;
    
    private String fecha;
}
