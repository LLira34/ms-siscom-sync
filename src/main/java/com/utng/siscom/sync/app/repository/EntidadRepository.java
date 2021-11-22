package com.utng.siscom.sync.app.repository;

import com.utng.siscom.sync.app.domain.Entidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntidadRepository extends JpaRepository<Entidad, Long> {
}
