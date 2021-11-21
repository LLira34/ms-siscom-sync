package com.utng.siscom.sync.app.web.rest;

import com.utng.siscom.sync.app.domain.Entidad;
import com.utng.siscom.sync.app.service.EntidadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EntidadRestController {
    private static final Logger log = LoggerFactory.getLogger(EntidadRestController.class);

    @Autowired
    private EntidadService entidadService;

    @GetMapping("/entidades")
    public List<Entidad> findAll() {
        log.info("findAll entidades");
        return entidadService.findAll();
    }

    @GetMapping("/entidades/{id}")
    public Entidad findById(@PathVariable Long id) {
        log.info("findById entidad");
        return entidadService.findById(id);
    }

    @PostMapping("/entidades")
    @ResponseStatus(HttpStatus.CREATED)
    public Entidad insert(@RequestBody Entidad data) {
        log.info("Insert entidad");
        return entidadService.save(data);
    }

    @PutMapping("/entidades/{id}")
    public Entidad update(@RequestBody Entidad data, @PathVariable Long id) {
        log.info("Update entidad");
        Entidad entidad = entidadService.findById(id);
        entidad.setNombre(data.getNombre());
        entidad.setAlias(data.getAlias());
        entidad.setActivo(data.getActivo());

        return entidadService.save(entidad);
    }

    @DeleteMapping("/entidades/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("Delete entidad");
        entidadService.delete(id);
    }
}
