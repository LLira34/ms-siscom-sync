package com.utng.siscom.sync.app.web.rest;

import com.utng.siscom.sync.app.domain.Entidad;
import com.utng.siscom.sync.app.service.EntidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EntidadRestController {

    @Autowired
    private EntidadService entidadService;

    @GetMapping("/entidades")
    public List<Entidad> findAll() {
        return entidadService.findAll();
    }
}
