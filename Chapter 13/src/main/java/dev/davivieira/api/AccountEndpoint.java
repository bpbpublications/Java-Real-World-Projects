package dev.davivieira.api;

import dev.davivieira.data.entity.Account;
import dev.davivieira.data.repository.AccountRepository;
import dev.davivieira.service.AccountService;
import dev.davivieira.service.payload.AccountPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountEndpoint {

    private final AccountService accountService;

    private final AccountRepository accountRepository;

    @Autowired
    private AccountEndpoint(AccountService accountService, AccountRepository accountRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    @PostMapping("/account")
    public Account createAccount(@RequestBody AccountPayload accountPayload) throws Exception {
        return accountService.createAccount(accountPayload);
    }

    @GetMapping("/account/{email}")
    public Account getAccount(@PathVariable String email) throws Exception {
        return accountRepository.findByEmail(email).orElseThrow(() -> new Exception("Account not found"));
    }
}