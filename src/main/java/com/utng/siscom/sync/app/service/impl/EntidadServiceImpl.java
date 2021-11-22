package com.utng.siscom.sync.app.service.impl;

import com.utng.siscom.sync.app.common.exceptions.SiscomException;
import com.utng.siscom.sync.app.domain.Entidad;
import com.utng.siscom.sync.app.repository.EntidadRepository;
import com.utng.siscom.sync.app.service.EntidadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return entidadRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Entidad> findAll(Pageable pageable) {
        log.info("findAll pageable");
        return entidadRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Entidad findById(Long id) {
        log.info("Id: {}", id);
        return entidadRepository.findById(id).orElseThrow(() -> new SiscomException("No se encontro el recurso"));
    }

    @Override
    @Transactional
    public Entidad insert(Entidad data) {
        log.info("Data: {}", data.toString());
        Entidad entidad = this.validateData(data);
        return entidadRepository.save(entidad);
    }

    @Override
    @Transactional
    public Entidad update(Entidad data, Long id) {
        Entidad entidad = this.findById(id);
        Entidad validate = this.validateData(data);
        entidad.setNombre(validate.getNombre());
        entidad.setAlias(validate.getAlias());
        entidad.setActivo(validate.getActivo());
        return entidadRepository.save(entidad);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Id: {}", id);
        entidadRepository.deleteById(id);
    }

    private Entidad validateData(Entidad data) {
        if (data.getActivo() == null) {
            data.setActivo(Boolean.FALSE);
        }
        if (data.getNombre() == null || data.getNombre().isEmpty() || data.getNombre().trim().equals("")) {
            throw new SiscomException("El campo [Nombre] es obligatorio");
        }
        if (data.getAlias() == null || data.getAlias().isEmpty() || data.getAlias().trim().equals("")) {
            throw new SiscomException("El campo [Alias] es obligatorio");
        }
        return data;
    }
}
