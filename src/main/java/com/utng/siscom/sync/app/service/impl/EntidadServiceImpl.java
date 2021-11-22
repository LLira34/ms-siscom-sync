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

import java.util.ArrayList;
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
        List<String> errors = this.validateErrors(entidad);
        if (!errors.isEmpty()) {
            throw new SiscomException("Error de datos", errors);
        }
        return entidadRepository.save(entidad);
    }

    @Override
    @Transactional
    public Entidad update(Entidad data, Long id) {
        Entidad entidad = this.findById(id);
        Entidad validate = this.validateData(data);
        List<String> errors = this.validateErrors(validate);
        if (!errors.isEmpty()) {
            throw new SiscomException("Error de datos", errors);
        }
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
        return data;
    }

    private List<String> validateErrors(Entidad data) {
        List<String> errors = new ArrayList<>();
        if (data.getNombre() == null || data.getNombre().isEmpty() || data.getNombre().trim().equals("")) {
            errors.add("El campo [Nombre] es obligatorio");
        }
        if (data.getAlias() == null || data.getAlias().isEmpty() || data.getAlias().trim().equals("")) {
            errors.add("El campo [Alias] es obligatorio");
        }
        if (data.getAlias() != null && (data.getAlias().length() < 2 || data.getAlias().length() > 4)) {
            errors.add("El campo [Alias] debe tener un tamaño entre 2 y 4 caracteres");
        }
        return errors;
    }
}
