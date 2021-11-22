package com.utng.siscom.sync.app.common.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

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
}
