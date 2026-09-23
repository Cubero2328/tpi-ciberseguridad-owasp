package com.cfp8.smata.integrador_ciberseguridad.proyecto_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cfp8.smata.integrador_ciberseguridad.proyecto_2.model.Factura;

import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    // Buscará facturas que pertenezcan solo a un propietario específico
    List<Factura> findByPropietario(String propietario);
}
