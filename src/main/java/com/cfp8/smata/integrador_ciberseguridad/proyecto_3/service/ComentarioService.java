package com.cfp8.smata.integrador_ciberseguridad.proyecto_3.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfp8.smata.integrador_ciberseguridad.proyecto_3.dto.ComentarioDTO;
import com.cfp8.smata.integrador_ciberseguridad.proyecto_3.model.Comentario;
import com.cfp8.smata.integrador_ciberseguridad.proyecto_3.repository.ComentarioRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository repository;

    @PostConstruct
    public void cargarDatosPrueba() {
        if (repository.count() == 0) {
            repository.save(new Comentario(null, "Carlos", "¡Excelente aplicación web, felicitaciones!",
                    LocalDate.now().toString()));
            repository.save(new Comentario(null, "Marta", "Me parece una excelente idea centralizar la seguridad.",
                    LocalDate.now().toString()));
        }
    }

    public List<ComentarioDTO> listarTodos() {
        return repository.findAll().stream()
                .map(c -> new ComentarioDTO(c.getId(), c.getAutor(), c.getContenido(), c.getFecha()))
                .collect(Collectors.toList());
    }

    /**
     * VULNERABLE: Guarda el texto exactamente igual a como lo envía el usuario.
     * Si viene un <script>, se almacena de forma literal.
     */
    public ComentarioDTO guardarVulnerable(ComentarioDTO dto) {
        Comentario comentario = new Comentario(null, dto.getAutor(), dto.getContenido(), LocalDate.now().toString());
        comentario = repository.save(comentario);
        return new ComentarioDTO(comentario.getId(), comentario.getAutor(), comentario.getContenido(),
                comentario.getFecha());
    }

    /**
     * MITIGADO: Sanitiza el texto convirtiendo los caracteres especiales en
     * entidades HTML seguras.
     * Por ejemplo: '<' se convierte en '&lt;' y '>' en '&gt;'. El navegador lo
     * leerá como texto y no como código ejecutable.
     */
    public ComentarioDTO guardarMitigado(ComentarioDTO dto) {
        String contenidoSeguro = escaparHtml(dto.getContenido());
        Comentario comentario = new Comentario(null, dto.getAutor(), contenidoSeguro, LocalDate.now().toString());
        comentario = repository.save(comentario);
        return new ComentarioDTO(comentario.getId(), comentario.getAutor(), comentario.getContenido(),
                comentario.getFecha());
    }

    // Método manual y sencillo de escape para no obligarte a descargar librerías
    // externas pesadas
    private String escaparHtml(String original) {
        if (original == null)
            return null;
        return original.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;")
                .replace("/", "&#x2F;");
    }
}