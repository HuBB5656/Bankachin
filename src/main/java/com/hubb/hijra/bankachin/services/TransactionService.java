package com.hubb.hijra.bankachin.services;

import com.hubb.hijra.bankachin.controllers.dto.AccountResponse;
import com.hubb.hijra.bankachin.controllers.dto.TransactionRequest;
import com.hubb.hijra.bankachin.controllers.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private AccountService accountService;

    @Transactional
    public TransactionResponse creditAccount(TransactionRequest request ) {
        if(request.getAmount().compareTo(BigDecimal.ZERO) <= 0){
            throw  new  IllegalArgumentException("Amount must be greater than zero");
        }


         AccountResponse account = accountService.updateAccountBalance(request.getAccountNumber(),request.getAmount(),"Credit",generateReferenceNumber());

          TransactionResponse transactionResponse = new TransactionResponse();
          transactionResponse.setSuccess(Boolean.TRUE);
          transactionResponse.setMessage("Account number "+account.getAccountNumber()+" Credit with ETB "+ request.getAmount()+" your account Balance "+ account.getBalance());
          transactionResponse.setAccount(account);
          return transactionResponse;
    }



    @Transactional
    public TransactionResponse debitAccount(TransactionRequest request ) {
        if(request.getAmount().compareTo(BigDecimal.ZERO) <= 0){
            throw  new  IllegalArgumentException("Amount must be greater than zero");
        }
        AccountResponse account = accountService.updateAccountBalance(request.getAccountNumber(),request.getAmount(),"Debit",generateReferenceNumber());
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setSuccess(Boolean.TRUE);
        transactionResponse.setMessage("Account number "+ account.getAccountNumber()+" Debit with ETB "+ request.getAmount()+" your new Balance"+ account.getBalance());
        transactionResponse.setAccount(account);

//       save customer transaction on ledger

        return transactionResponse;
    }


    public static String generateReferenceNumber() {
        return "REF-" + UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 12)
                .toUpperCase();
    }
}
