package com.utng.siscom.sync.app.service;

import com.utng.siscom.sync.app.domain.Entidad;

import java.util.List;

public interface EntidadService {

    List<Entidad> findAll();

    Entidad findById(Long id);

    Entidad save(Entidad data);

    void delete(Long id);
}
