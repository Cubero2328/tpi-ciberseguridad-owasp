package com.cfp8.smata.integrador_ciberseguridad.proyecto_1.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfp8.smata.integrador_ciberseguridad.proyecto_1.dto.UsuarioResponseDTO;
import com.cfp8.smata.integrador_ciberseguridad.proyecto_1.model.UsuarioSql;
import com.cfp8.smata.integrador_ciberseguridad.proyecto_1.repository.UsuarioSqlRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioSqlService {

    @Autowired
    private UsuarioSqlRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    // DATA DE PRUEBA: Inserta registros automáticamente al iniciar para poder testear
    @PostConstruct
    public void cargarDatosPrueba() {
        if (repository.count() == 0) {
            repository.save(new UsuarioSql(null, "admin", "admin@empresa.com", "ClaveMaestra123", "ADMIN"));
            repository.save(new UsuarioSql(null, "juan99", "juan@correo.com", "juanito123", "USER"));
            repository.save(new UsuarioSql(null, "ana_security", "ana@correo.com", "anaSecure9x", "USER"));
        }
    }

    /**
     * VULNERABLE: Utiliza concatenación directa de strings en una consulta SQL.
     * Si el usuario ingresa: ' OR '1'='1 , expondrá toda la base de datos.
     */
    @SuppressWarnings("unchecked")
    public List<UsuarioResponseDTO> buscarVulnerable(String criterio) {
        String query = "SELECT * FROM usuarios_sql WHERE username LIKE '%" + criterio + "%'";
        
        List<UsuarioSql> resultados = entityManager.createNativeQuery(query, UsuarioSql.class).getResultList();
        
        return resultados.stream()
                .map(u -> new UsuarioResponseDTO(u.getId(), u.getUsername(), u.getEmail(), u.getRol()))
                .collect(Collectors.toList());
    }

    /**
     * MITIGADO: Utiliza el motor de Spring Data JPA que limpia y parametriza los argumentos de consulta.
     */
    public List<UsuarioResponseDTO> buscarMitigado(String criterio) {
        List<UsuarioSql> resultados = repository.findByUsernameContaining(criterio);
        
        return resultados.stream()
                .map(u -> new UsuarioResponseDTO(u.getId(), u.getUsername(), u.getEmail(), u.getRol()))
                .collect(Collectors.toList());
    }
}
