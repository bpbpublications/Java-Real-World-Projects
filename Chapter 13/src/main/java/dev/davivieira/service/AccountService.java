package dev.davivieira.service;

import dev.davivieira.data.entity.Account;
import dev.davivieira.data.repository.AccountRepository;
import dev.davivieira.service.payload.AccountPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(AccountPayload accountPayload) throws Exception {
        validateEmail(accountPayload);
        var account = Account.builder()
                .id(accountPayload.getId())
                .email(accountPayload.getEmail())
                .password(accountPayload.getPassword())
                .categories(List.of())
                .transactions(List.of())
                .build();
        accountRepository.save(account);
        return account;
    }

    private void validateEmail(AccountPayload accountPayload) throws Exception {
        if (!Pattern.matches("^(.+)@(\\S+)$", accountPayload.getEmail())) {
            throw new Exception("Email format name is invalid.");
        }
        if (accountRepository.findByEmail(accountPayload.getEmail()).isPresent()) {
            throw new Exception("Email provided already exists.");
        }
    }

}