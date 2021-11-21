package com.utng.siscom.sync.app.service.impl;

import com.utng.siscom.sync.app.domain.Entidad;
import com.utng.siscom.sync.app.repository.EntidadRepository;
import com.utng.siscom.sync.app.service.EntidadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EntidadServiceImpl implements EntidadService {
    private static final Logger log = LoggerFactory.getLogger(EntidadServiceImpl.class);

    @Autowired
    private EntidadRepository entidadRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Entidad> findAll() {
        log.info("findAll");
        return (List<Entidad>) entidadRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Entidad findById(Long id) {
        log.info("Id: {}", id);
        return entidadRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Entidad save(Entidad data) {
        log.info("Data: {}", data.toString());
        if (data.getActivo() == null) {
            data.setActivo(Boolean.FALSE);
        }
        return entidadRepository.save(data);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Id: {}", id);
        entidadRepository.deleteById(id);
    }
}
