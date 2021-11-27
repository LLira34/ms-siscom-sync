package com.utng.siscom.sync.app.service;

import com.utng.siscom.sync.app.domain.Ciudad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CiudadService {
    List<Ciudad> findAll();

    Page<Ciudad> findAll(Pageable pageable);

    Ciudad findById(Long id);

    Ciudad insert(Ciudad data);

    Ciudad update(Ciudad data, Long id);

    void delete(Long id);
}
