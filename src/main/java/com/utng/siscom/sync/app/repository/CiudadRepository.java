package com.utng.siscom.sync.app.repository;

import com.utng.siscom.sync.app.domain.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
}
