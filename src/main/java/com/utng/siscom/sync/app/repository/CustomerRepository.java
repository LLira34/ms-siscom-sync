package com.utng.siscom.sync.app.repository;

import com.utng.siscom.sync.app.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
