package com.nhnacademy.springbootaccount;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAll();
    Optional<Account> findById(Long id);
}
