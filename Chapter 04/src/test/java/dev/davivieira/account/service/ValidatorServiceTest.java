package dev.davivieira.account.service;

import dev.davivieira.account.vo.AccountPayload;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorServiceTest {

    private final ValidatorService validatorService = new ValidatorService();

    @Test
    public void givenInValidEmailString_thenValidationThrowsException() {
        // Arrange
        var email = "@daviveira.dev";
        var password = "123456";
        var birthDate = LocalDate.of(1980, 1, 1);

        // Prepare
        var accountPayload = getAccountPayload(email, password, birthDate);

        // Execute and Pre Assert
        Exception exception = assertThrows(Exception.class, () -> {
            validatorService.validateAccount(accountPayload);
        });

        // Post Assert
        String expected = "Email format is not valid";
        String actual = exception.getMessage();
        assertEquals(actual, expected);
    }

    @Test
    public void givenPasswordIsThanSixCharacters_thenValidationIsTrue() {
        // Arrange
        var email = "user@daviveira.dev";
        var password = "123";
        var birthDate = LocalDate.of(1980, 1, 1);

        // Prepare
        var accountPayload = getAccountPayload(email, password, birthDate);

        // Execute and Pre Assert
        Exception exception = assertThrows(Exception.class, () -> {
            validatorService.validateAccount(accountPayload);
        });

        // Post Assert
        String expected = "Password must have at least 6 characters";
        String actual = exception.getMessage();
        assertEquals(actual, expected);
    }

    @Test
    public void givenBirthDateIsLowerThan18_thenValidationIsFalse() {
        // Arrange
        var email = "user@daviveira.dev";
        var password = "123456";
        var birthDate = LocalDate.of(2010, 1, 1);

        // Prepare
        var accountPayload = getAccountPayload(email, password, birthDate);

        // Execute and Pre Assert
        Exception exception = assertThrows(Exception.class, () -> {
            validatorService.validateAccount(accountPayload);
        });

        // Post Assert
        String expected = "Age must be at least 18 years old";
        String actual = exception.getMessage();
        assertEquals(actual, expected);
    }

    private AccountPayload getAccountPayload(String email, String password, LocalDate birthDate) {
        return new AccountPayload(email, password, birthDate);
    }
}
