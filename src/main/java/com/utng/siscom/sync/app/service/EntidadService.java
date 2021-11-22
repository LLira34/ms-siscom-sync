package com.utng.siscom.sync.app.service;

import com.utng.siscom.sync.app.domain.Entidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EntidadService {

    List<Entidad> findAll();

    Page<Entidad> findAll(Pageable pageable);

    Entidad findById(Long id);

    Entidad insert(Entidad data);

    Entidad update(Entidad data, Long id);

    void delete(Long id);
}
