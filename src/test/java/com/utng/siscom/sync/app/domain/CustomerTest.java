package com.utng.siscom.sync.app.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTest {

    @Test
    void return_true_if_id_is_long() {
        // Esperado
        Long expected = 1L;

        // Ejecutar y obtener
        Customer customer = new Customer();
        customer.setId(1L);

        // Comparar esperado vs obtenido
        assertEquals(expected, customer.getId());
    }
}