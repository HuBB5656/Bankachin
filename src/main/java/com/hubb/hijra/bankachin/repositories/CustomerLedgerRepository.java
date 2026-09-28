package com.hubb.hijra.bankachin.repositories;

import com.hubb.hijra.bankachin.models.CustomerLedger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerLedgerRepository extends JpaRepository<CustomerLedger,Long> {

    List<CustomerLedger> findByAccount_AccountNumberOrderByTransactionDateDesc(String accountNumber);
}
