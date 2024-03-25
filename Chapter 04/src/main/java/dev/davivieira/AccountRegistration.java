package dev.davivieira;

import dev.davivieira.account.repository.AccountRepository;
import dev.davivieira.account.service.RegistrationService;
import dev.davivieira.account.service.ValidatorService;
import dev.davivieira.account.vo.AccountPayload;

import java.time.LocalDate;

public class AccountRegistration {

    public static void main(String... args) throws Exception {
        var validatorService = new ValidatorService();
        var accountRepository = new AccountRepository();
        var registrationService = new RegistrationService(validatorService, accountRepository);
        registrationService.register(new AccountPayload(
                "test@davivieira.dev",
                "123456",
                LocalDate.of(1980, 1, 1)
        ));
        System.out.println(accountRepository.findByEmail("test@davivieira.dev"));
    }

}
