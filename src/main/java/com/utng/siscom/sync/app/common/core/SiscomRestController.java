package com.utng.siscom.sync.app.common.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SiscomRestController {

    protected ResponseEntity<?> ok(Map<String, Object> params) {
        return new ResponseEntity<>(params, HttpStatus.OK);
    }

    protected ResponseEntity<?> ok(Object object) {
        return ResponseEntity.ok(object);
    }

    protected ResponseEntity<?> created(Map<String, Object> params) {
        return new ResponseEntity<>(params, HttpStatus.CREATED);
    }

    protected ResponseEntity<?> noContent() {
        return ResponseEntity.noContent().build();
    }

    protected ResponseEntity<?> badRequest(Map<String, Object> params) {
        return ResponseEntity.badRequest().body(params);
    }

    protected ResponseEntity<?> notFound(Map<String, Object> params) {
        return new ResponseEntity<>(params, HttpStatus.NOT_FOUND);
    }

    protected ResponseEntity<?> internalServerError(Map<String, Object> params) {
        return ResponseEntity.internalServerError().body(params);
    }

    protected ResponseEntity<?> validateField(BindingResult result) {
        Map<String, Object> params = new HashMap<>();
        List<String> errors = result.getFieldErrors().stream()
                .map(e -> "El campo [" + e.getField().toUpperCase() + "] " + e.getDefaultMessage())
                .collect(Collectors.toList());
        params.put("errors", errors);
        params.put("message", "Error de datos");
        return badRequest(params);
    }
}
