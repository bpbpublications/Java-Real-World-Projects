package dev.davivieira.account.service;

import dev.davivieira.account.repository.AccountRepository;
import dev.davivieira.account.vo.AccountPayload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static dev.davivieira.account.vo.Status.ACTIVE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceTest {

    private RegistrationService registrationService;

    @BeforeEach
    void init(@Mock AccountRepository accountRepository) {
        registrationService = new RegistrationService(new ValidatorService(), accountRepository);
        doNothing().when(accountRepository).persist(any());
    }

    @Test
    public void givenValidAccountPayload_thenAccountObjectIsCreated() throws Exception {
        // Arrange
        var email = "user@daviveira.dev";
        var password = "123456";
        var birthDate = LocalDate.of(1980, 1, 1);

        // Prepare
        var accountPayload = getAccountPayload(email, password, birthDate);

        // Execute
        var account = registrationService.register(accountPayload);

        // Assert
        assertAll("Account is properly created",
                () -> assertEquals(account.email(), email),
                () -> assertEquals(account.password(), password),
                () -> assertEquals(account.birthDate(), birthDate),
                () -> assertEquals(account.status(), ACTIVE)
        );
    }

    private AccountPayload getAccountPayload(String email, String password, LocalDate birthDate) {
        return new AccountPayload(email, password, birthDate);
    }
}
