package com.hubb.hijra.bankachin.controllers.dto;


import com.hubb.hijra.bankachin.models.Accounts;
import com.hubb.hijra.bankachin.models.CustomerLedger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
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

    public CustomerLedgerRequests(CustomerLedger customerLedger) {
        this.account = customerLedger.getAccount();
        this.balanceBefore = customerLedger.getBalanceBefore();
        this.amount = customerLedger.getAmount();
        this.balanceAfter = customerLedger.getBalanceAfter();
        this.transactionDate = customerLedger.getTransactionDate();
        this.reference = customerLedger.getReference();
        this.description = customerLedger.getDescription();
    }


}
