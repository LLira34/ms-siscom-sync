package com.utng.siscom.sync.app.web.rest;

import com.utng.siscom.sync.app.common.core.SiscomRestController;
import com.utng.siscom.sync.app.common.exceptions.SiscomException;
import com.utng.siscom.sync.app.domain.Entidad;
import com.utng.siscom.sync.app.service.EntidadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EntidadRestController extends SiscomRestController {
    private static final Logger log = LoggerFactory.getLogger(EntidadRestController.class);

    @Autowired
    private EntidadService entidadService;

    @GetMapping("/entidades")
    public ResponseEntity<?> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                     @RequestParam(name = "limit", defaultValue = "5") Integer limit,
                                     @RequestParam(name = "sort", defaultValue = "id") String sort,
                                     @RequestParam(name = "order", defaultValue = "desc") String order) {
        log.info("findAll entidades");
        Map<String, Object> params = new HashMap<>();
        Page<Entidad> entidades;
        try {
            Pageable pageable = order.equals("asc")
                    ? PageRequest.of(page, limit, Sort.by(sort))
                    : PageRequest.of(page, limit, Sort.by(sort).descending());
            entidades = entidadService.findAll(pageable);
        } catch (DataAccessException e) {
            params.put("message", "Error al consultar los registros");
            params.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return internalServerError(params);
        }
        return ok(entidades);
    }

    @GetMapping("/entidades/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        log.info("findById entidad");
        Entidad entidad;
        Map<String, Object> params = new HashMap<>();
        try {
            entidad = entidadService.findById(id);
        } catch (SiscomException e) {
            params.put("message", e.getMessage());
            return notFound(params);
        } catch (DataAccessException e) {
            params.put("message", "Error al consultar el registro");
            params.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return internalServerError(params);
        }
        return ok(entidad);
    }

    @PostMapping("/entidades")
    public ResponseEntity<?> insert(@Valid @RequestBody Entidad data, BindingResult result) {
        log.info("Insert entidad");
        Entidad entidad;
        Map<String, Object> params = new HashMap<>();
        if (result.hasErrors()) {
            return validateField(result);
        }
        try {
            entidad = entidadService.insert(data);
        } catch (SiscomException e) {
            params.put("message", e.getMessage());
            return badRequest(params);
        } catch (DataAccessException e) {
            params.put("message", "Error al insertar el registro");
            params.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return internalServerError(params);
        }
        params.put("id", entidad.getId());
        return created(params);
    }

    @PutMapping("/entidades/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Entidad data, BindingResult result, @PathVariable Long id) {
        log.info("Update entidad");
        Entidad entidad;
        Map<String, Object> params = new HashMap<>();
        if (result.hasErrors()) {
            return validateField(result);
        }
        try {
            entidad = entidadService.update(data, id);
        } catch (SiscomException e) {
            params.put("message", e.getMessage());
            return badRequest(params);
        } catch (DataAccessException e) {
            params.put("message", "Error al editar el registro");
            params.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return internalServerError(params);
        }
        return ok(entidad);
    }

    @DeleteMapping("/entidades/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        log.info("Delete entidad");
        Map<String, Object> params = new HashMap<>();
        try {
            entidadService.delete(id);
        } catch (SiscomException e) {
            params.put("message", e.getMessage());
            return badRequest(params);
        } catch (DataAccessException e) {
            params.put("message", "Error al eliminar el registro");
            params.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return internalServerError(params);
        }
        return noContent();
    }
}
