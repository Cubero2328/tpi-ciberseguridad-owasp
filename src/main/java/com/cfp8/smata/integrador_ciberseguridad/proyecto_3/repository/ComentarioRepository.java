package com.cfp8.smata.integrador_ciberseguridad.proyecto_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cfp8.smata.integrador_ciberseguridad.proyecto_3.model.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
