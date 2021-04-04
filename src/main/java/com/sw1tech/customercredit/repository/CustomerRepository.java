package com.sw1tech.customercredit.repository;

import com.sw1tech.customercredit.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByName(String name);
}
