package com.hubb.hijra.bankachin.controllers.dto;


import com.hubb.hijra.bankachin.models.Accounts;
import com.hubb.hijra.bankachin.models.CustomerLedger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerLedgerRequests {

    private Accounts account;
    private BigDecimal balanceBefore;
    private BigDecimal amount;
    private BigDecimal balanceAfter;
    private  String reference;
    private String description;
    private LocalDateTime transactionDate;



}
