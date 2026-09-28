package com.hubb.hijra.bankachin.services;


import com.hubb.hijra.bankachin.controllers.dto.AccountResponse;
import com.hubb.hijra.bankachin.models.Accounts;
import com.hubb.hijra.bankachin.models.CustomerLedger;
import com.hubb.hijra.bankachin.repositories.AccountRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepositories accountRepositories;
    private  final CustomerLedgerService customerLedgerService;

    public List<Accounts> getAccounts() {

        return accountRepositories.findAll();
    }
    public AccountResponse createAccount(Accounts accounts){
          Accounts newAccount =  accountRepositories.save(accounts);
          return new AccountResponse(newAccount);
    }



    public AccountResponse getAccountByAccountNumber(String accountNumber){
        Accounts account = accountRepositories.findByAccountNumber(accountNumber).orElseThrow(()-> new RuntimeException("account not found"));

        return new AccountResponse(account);

    }
//     update customer balance
    public AccountResponse updateAccountBalance(String accountNumber, BigDecimal amount, String direction,String referenceNumber){
        Accounts account = accountRepositories.findByAccountNumber(accountNumber).orElseThrow(()-> new RuntimeException("account not found"));

        if(!"Active".equals(account.getAccountStatus())){
            throw  new  IllegalArgumentException("Account is not active");
        }

        BigDecimal balanceBefore = account.getBalance();

        BigDecimal balanceAfter;

        if ("Credit".equals(direction)) {
            balanceAfter = balanceBefore.add(amount);
        } else if ("Debit".equals(direction)) {
            balanceAfter = balanceBefore.subtract(amount);
            if(balanceAfter.compareTo(BigDecimal.ZERO)<=0){
                throw  new  IllegalArgumentException("Insufficient balance please try again later!");
            }
        } else {
            throw new IllegalArgumentException("Invalid transaction direction: " + direction);
        }

        account.setBalance(balanceAfter);

        accountRepositories.save(account);
        CustomerLedger customerLedger = new CustomerLedger();
        customerLedger.setReference(referenceNumber);
        customerLedger.setAccount(account);
        customerLedger.setBalanceBefore(balanceBefore);
        customerLedger.setAmount(amount);
        customerLedger.setBalanceAfter(balanceAfter);
        customerLedger.setDescription("Account Number " + account.getAccountNumber()+ " " + direction +  " with " + amount );

        customerLedgerService.createTrancastionRow(customerLedger);

        return new  AccountResponse(account);
    }
    public AccountResponse deActivateAccount(String AccountNumber){
         Accounts account = accountRepositories.findByAccountNumber(AccountNumber).orElseThrow(() -> new RuntimeException("Account Not Found!."));
         account.setAccountStatus("InAcive");
         accountRepositories.save(account);
         return new AccountResponse(account);

    }

    public AccountResponse activateAccount(String AccountNumber){
        Accounts account = accountRepositories.findByAccountNumber(AccountNumber).orElseThrow(()-> new RuntimeException("Account Not Found!."));
        account.setAccountStatus("Active");
        accountRepositories.save(account);

        return new AccountResponse(account);

    }

}
