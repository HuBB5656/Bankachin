package com.hubb.hijra.bankachin.services;


import com.hubb.hijra.bankachin.controllers.dto.CustomerLedgerRequests;
import com.hubb.hijra.bankachin.models.CustomerLedger;
import com.hubb.hijra.bankachin.repositories.AccountRepositories;
import com.hubb.hijra.bankachin.repositories.CustomerLedgerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerLedgerService {

    @Autowired
    private CustomerLedgerRepository customerLedgerRepository;
    private AccountRepositories accountRepositories;

    public CustomerLedger createTrancastionRow(CustomerLedger customerLedger) {
        return customerLedgerRepository.save(customerLedger);
    }
    public List<CustomerLedger> gatAccountStatment(String accountNumber){
        accountRepositories.findByAccountNumber(accountNumber).orElseThrow(() -> new RuntimeException("Account number not found"));
        return customerLedgerRepository.findByAccount_AccountNumberOrderByTransactionDateDesc(accountNumber);
    }
}
