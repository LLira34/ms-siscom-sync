package com.utng.siscom.sync.app.web.rest;

import com.utng.siscom.sync.app.common.core.SiscomRestController;
import com.utng.siscom.sync.app.common.exceptions.SiscomException;
import com.utng.siscom.sync.app.domain.Ciudad;
import com.utng.siscom.sync.app.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CiudadRestController extends SiscomRestController {
    @Autowired
    private CiudadService ciudadService;

    @GetMapping("/ciudades")
    public ResponseEntity<?> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                     @RequestParam(name = "limit", defaultValue = "5") Integer limit,
                                     @RequestParam(name = "sort", defaultValue = "id") String sort,
                                     @RequestParam(name = "order", defaultValue = "desc") String order) {
        Map<String, Object> params = new HashMap<>();
        Page<Ciudad> ciudades;
        try {
            Pageable pageable = order.equals("asc")
                    ? PageRequest.of(page, limit, Sort.by(sort))
                    : PageRequest.of(page, limit, Sort.by(sort).descending());
            ciudades = ciudadService.findAll(pageable);
        } catch (DataAccessException e) {
            params.put("message", "Error al consultar los registros");
            params.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return internalServerError(params);
        }
        return ok(ciudades);
    }

    @PostMapping("/ciudades")
    public ResponseEntity<?> insert(@RequestBody Ciudad data) {
        Ciudad ciudad;
        Map<String, Object> params = new HashMap<>();
        try {
            ciudad = ciudadService.insert(data);
        } catch (SiscomException e) {
            params.put("message", e.getMessage());
            params.put("errors", e.getErrors());
            return badRequest(params);
        } catch (DataAccessException e) {
            params.put("message", "Error al insertar el registro");
            params.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return internalServerError(params);
        }
        params.put("id", ciudad.getId());
        return created(params);
    }
}
