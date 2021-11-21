package com.utng.siscom.sync.app.service.impl;

import com.utng.siscom.sync.app.domain.Entidad;
import com.utng.siscom.sync.app.repository.EntidadRepository;
import com.utng.siscom.sync.app.service.EntidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntidadServiceImpl implements EntidadService {

    @Autowired
    private EntidadRepository entidadRepository;

    @Override
    public List<Entidad> findAll() {
        return (List<Entidad>) entidadRepository.findAll();
    }
}
