package com.sw1tech.customercredit.repository;

import com.sw1tech.customercredit.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByName(String name);

    @Modifying
    @Transactional
    @Query("update Customer c set c.limitcredit = ?1 where c.id = ?2")
    int saveLimit(double limitCredit, int id);
}
