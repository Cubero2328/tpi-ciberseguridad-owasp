package com.cfp8.smata.integrador_ciberseguridad.proyecto_1.repository;

import com.cfp8.smata.integrador_ciberseguridad.proyecto_1.model.UsuarioSql;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import java.util.List;

@Repository
public interface UsuarioSqlRepository extends JpaRepository<UsuarioSql, Long> {
    
    // LA SOLUCIÓN MITIGADA: Spring Data JPA parametriza automáticamente el input de forma segura
    List<UsuarioSql> findByUsernameContaining(String username);
}
