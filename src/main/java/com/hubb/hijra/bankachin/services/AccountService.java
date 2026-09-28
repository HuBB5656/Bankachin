package com.hubb.hijra.bankachin.services;


import com.hubb.hijra.bankachin.controllers.dto.AccountResponse;
import com.hubb.hijra.bankachin.models.Accounts;
import com.hubb.hijra.bankachin.repositories.AccountRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
