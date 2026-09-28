package com.hubb.hijra.bankachin.controllers.dto;

import com.hubb.hijra.bankachin.models.Accounts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private String phoneNumber;
    private String accountStatus;
    private LocalDateTime createdDate;


    public AccountResponse(Accounts accounts) {
        this.id = accounts.getId();
        this.accountNumber = accounts.getAccountNumber();
        this.balance = accounts.getBalance();
        this.phoneNumber = accounts.getPhoneNumber();
        this.accountStatus = accounts.getAccountStatus();

    }
}
