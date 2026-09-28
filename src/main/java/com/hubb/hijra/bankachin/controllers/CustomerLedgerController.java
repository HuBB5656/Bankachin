package com.hubb.hijra.bankachin.controllers;


import com.hubb.hijra.bankachin.controllers.dto.CustomerLedgerRequests;
import com.hubb.hijra.bankachin.models.CustomerLedger;
import com.hubb.hijra.bankachin.services.CustomerLedgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerLedgerController {

    @Autowired
    private CustomerLedgerService customerLedgerService;

    @GetMapping("/statment/{accountNumber}")
    public List<CustomerLedger> findByAccountNumber(@PathVariable String accountNumber){
         return  customerLedgerService.gatAccountStatment(accountNumber);
    }

}
