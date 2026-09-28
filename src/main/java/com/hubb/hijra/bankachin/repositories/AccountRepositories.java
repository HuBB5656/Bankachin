package com.hubb.hijra.bankachin.repositories;

import com.hubb.hijra.bankachin.models.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepositories extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByAccountNumber(String accountNumber);

}
