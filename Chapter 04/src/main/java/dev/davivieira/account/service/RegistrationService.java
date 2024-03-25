package dev.davivieira.account.service;

import dev.davivieira.account.entity.Account;
import dev.davivieira.account.repository.AccountRepository;
import dev.davivieira.account.vo.AccountPayload;
import dev.davivieira.account.vo.Status;

import java.time.Instant;

public class RegistrationService {

    private final ValidatorService validatorService;
    private final AccountRepository accountRepository;

    public RegistrationService(ValidatorService validatorService, AccountRepository accountRepository) {
        this.validatorService = validatorService;
        this.accountRepository = accountRepository;
    }

    public Account register(AccountPayload accountPayload) throws Exception {
        validatorService.validateAccount(accountPayload);
        return createAccount(accountPayload);
    }

    public Account suspend(String email) {
        var account = accountRepository.findByEmail(email);
        return suspendAccount(account);
    }

    private Account createAccount(AccountPayload accountPayload) {
        var account = new Account(
                accountPayload.email(),
                accountPayload.password(),
                accountPayload.birthDate(),
                Instant.now(),
                Status.ACTIVE
        );
        accountRepository.persist(account);
        return account;
    }

    private Account suspendAccount(Account account) {
        var suspendeAccount = new Account(
                account.email(),
                account.password(),
                account.birthDate(),
                account.creationTimestamp(),
                Status.SUSPENDED
        );
        accountRepository.persist(suspendeAccount);
        return suspendeAccount;
    }
}
