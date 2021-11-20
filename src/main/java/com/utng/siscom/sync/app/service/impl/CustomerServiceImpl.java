package com.utng.siscom.sync.app.service.impl;

import com.utng.siscom.sync.app.domain.Customer;
import com.utng.siscom.sync.app.repository.CustomerRepository;
import com.utng.siscom.sync.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return (List<Customer>) customerRepository.findAll();
    }
}
