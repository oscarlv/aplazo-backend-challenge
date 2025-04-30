package com.aplazo.challenge.repository;

import com.aplazo.challenge.entity.Customer;
import com.aplazo.challenge.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LoanRepository extends JpaRepository<Loan, UUID> {
    Optional<Loan> findById(UUID id);
}
