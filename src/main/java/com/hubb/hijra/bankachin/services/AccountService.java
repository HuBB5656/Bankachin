package com.hubb.hijra.bankachin.services;


import com.hubb.hijra.bankachin.controllers.dto.AccountResponse;
import com.hubb.hijra.bankachin.models.Accounts;
import com.hubb.hijra.bankachin.repositories.AccountRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepositories accountRepositories;

    public List<Accounts> getAccounts() {

        return accountRepositories.findAll();
    }
    public AccountResponse createAccount(Accounts accounts){
          Accounts newAccount =  accountRepositories.save(accounts);
          AccountResponse newAccountResponse = new AccountResponse();
          newAccountResponse.setId(newAccount.getId());
          newAccountResponse.setBalance(newAccount.getBalance());
          newAccountResponse.setAccountStatus(newAccount.getAccountStatus());
          newAccountResponse.setAccountNumber(newAccount.getAccountNumber());
          newAccountResponse.setPhoneNumber(newAccount.getPhoneNumber());
          newAccountResponse.setCreatedDate(newAccount.getCreatedDate());
          return newAccountResponse;
    }



    public AccountResponse getAccountByAccountNumber(String accountNumber){
        Accounts account = accountRepositories.findByAccountNumber(accountNumber).orElseThrow(()-> new RuntimeException("account not found"));

        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(account.getId());
        accountResponse.setBalance(account.getBalance());
        accountResponse.setAccountStatus(account.getAccountStatus());
        accountResponse.setAccountNumber(account.getAccountNumber());
        accountResponse.setPhoneNumber(account.getPhoneNumber());
        accountResponse.setCreatedDate(account.getCreatedDate());
        return accountResponse;

    }
//     update customer balance
    public AccountResponse updateAccountBalance(String accountNumber, BigDecimal amount, String direction){
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




        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(account.getId());
        accountResponse.setBalance(balanceAfter);
        accountResponse.setAccountStatus(account.getAccountStatus());
        accountResponse.setAccountNumber(account.getAccountNumber());
        accountResponse.setPhoneNumber(account.getPhoneNumber());
        accountResponse.setCreatedDate(account.getCreatedDate());

        return accountResponse;
    }
    public AccountResponse deActivateAccount(String AccountNumber){
         Accounts account = accountRepositories.findByAccountNumber(AccountNumber).orElseThrow(() -> new RuntimeException("Account Not Found!."));
         account.setAccountStatus("InAcive");
         accountRepositories.save(account);

         AccountResponse accountResponse = new AccountResponse();
         accountResponse.setId(account.getId());
         accountResponse.setBalance(account.getBalance());
         accountResponse.setAccountStatus(account.getAccountStatus());
         accountResponse.setAccountNumber(account.getAccountNumber());
         accountResponse.setPhoneNumber(account.getPhoneNumber());
         accountResponse.setCreatedDate(account.getCreatedDate());
         return accountResponse;

    }

    public AccountResponse activateAccount(String AccountNumber){
        Accounts account = accountRepositories.findByAccountNumber(AccountNumber).orElseThrow(()-> new RuntimeException("Account Not Found!."));
        account.setAccountStatus("Active");
        accountRepositories.save(account);

        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(account.getId());
        accountResponse.setBalance(account.getBalance());
        accountResponse.setAccountStatus(account.getAccountStatus());
        accountResponse.setAccountNumber(account.getAccountNumber());
        accountResponse.setPhoneNumber(account.getPhoneNumber());
        accountResponse.setCreatedDate(account.getCreatedDate());
        return accountResponse;
    }

}
