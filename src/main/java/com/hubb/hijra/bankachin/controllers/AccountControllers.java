package com.hubb.hijra.bankachin.controllers;


import com.hubb.hijra.bankachin.controllers.dto.AccountResponse;
import com.hubb.hijra.bankachin.models.Accounts;
import com.hubb.hijra.bankachin.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountControllers {

    @Autowired
    private AccountService accountService;



    @GetMapping("/")
    public List<Accounts> getAllAccounts() {
        return accountService.getAccounts();
    }

    @PostMapping("/createAccount")
    public AccountResponse createAccount(@RequestBody Accounts accounts) {
        return accountService.createAccount(accounts);
    }

    @GetMapping("de-activate/{AccountNumber}")
    public AccountResponse deactivateAccount(@PathVariable String AccountNumber) {
        return accountService.deActivateAccount(AccountNumber);
    }

    @GetMapping("/activate/{AccountNumber}")
    public AccountResponse activateAccount(@PathVariable String AccountNumber) {
        return accountService.activateAccount(AccountNumber);
    }

}

