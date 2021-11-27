package com.utng.siscom.sync.app.service.impl;

import com.utng.siscom.sync.app.common.exceptions.SiscomException;
import com.utng.siscom.sync.app.domain.Ciudad;
import com.utng.siscom.sync.app.repository.CiudadRepository;
import com.utng.siscom.sync.app.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CiudadServiceImpl implements CiudadService {
    @Autowired
    private CiudadRepository ciudadRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Ciudad> findAll() {
        return ciudadRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Ciudad> findAll(Pageable pageable) {
        return ciudadRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Ciudad findById(Long id) {
        return ciudadRepository.findById(id).orElseThrow(() -> new SiscomException("No se encontro el recurso"));
    }

    @Override
    @Transactional
    public Ciudad insert(Ciudad data) {
        Ciudad ciudad = this.validateData(data);
        List<String> errors = this.validateErrors(ciudad);
        if (!errors.isEmpty()) {
            throw new SiscomException("Error de datos", errors);
        }
        return ciudadRepository.save(ciudad);
    }

    @Override
    @Transactional
    public Ciudad update(Ciudad data, Long id) {
        Ciudad ciudad = this.findById(id);
        Ciudad validate = this.validateData(data);
        List<String> errors = this.validateErrors(validate);
        if (!errors.isEmpty()) {
            throw new SiscomException("Error de datos", errors);
        }
        ciudad.setNombre(validate.getNombre());
        ciudad.setAlias(validate.getAlias());
        ciudad.setActivo(validate.getActivo());
        return ciudadRepository.save(ciudad);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ciudadRepository.deleteById(id);
    }

    private Ciudad validateData(Ciudad data) {
        if (data.getActivo() == null) {
            data.setActivo(Boolean.FALSE);
        }
        return data;
    }

    private List<String> validateErrors(Ciudad data) {
        List<String> errors = new ArrayList<>();
        if (data.getNombre() == null || data.getNombre().isEmpty() || data.getNombre().trim().equals("")) {
            errors.add("El campo [Nombre] es obligatorio");
        }
        if (data.getAlias() == null || data.getAlias().isEmpty() || data.getAlias().trim().equals("")) {
            errors.add("El campo [Alias] es obligatorio");
        }
        if (data.getAlias() != null && (data.getAlias().length() < 2 || data.getAlias().length() > 5)) {
            errors.add("El campo [Alias] debe tener un tamaño entre 2 y 4 caracteres");
        }
        return errors;
    }
}
