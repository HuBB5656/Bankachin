package com.hubb.hijra.bankachin.controllers;


import com.hubb.hijra.bankachin.controllers.dto.TransactionRequest;
import com.hubb.hijra.bankachin.controllers.dto.TransactionResponse;
import com.hubb.hijra.bankachin.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

  @Autowired
    private TransactionService transactionService;

  @PostMapping("credit-account")
    public TransactionResponse creditAccount(@RequestBody TransactionRequest request ) {
      return transactionService.creditAccount(request);
  }

  @PostMapping("debit-account")
  public TransactionResponse debitTransaction(@RequestBody TransactionRequest request ) {
    return transactionService.debitAccount(request);
  }
}
