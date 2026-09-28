package com.hubb.hijra.bankachin.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    private String accountNumber;
    private BigDecimal amount;
    private String reference;
    private String description;

}
