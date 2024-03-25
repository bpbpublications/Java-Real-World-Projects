package dev.davivieira.account.service;

import dev.davivieira.account.vo.AccountPayload;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class ValidatorService {

    public final static String INVALID_EMAIL_MESSAGE = "Email format is not valid";
    public final static String INVALID_PASSWORD_MESSAGE = "Password must have at least 6 characters";
    public final static String INVALID_BIRTHDATE_MESSAGE = "Age must be at least 18 years old";

    public void validateAccount(AccountPayload accountPayload) throws Exception {
        var isEmailValid = validateEmail(accountPayload.email());
        if(!isEmailValid) {
            throw new Exception(INVALID_EMAIL_MESSAGE);
        }
        var isPasswordValid = validatePassword(accountPayload.password());
        if(!isPasswordValid) {
            throw new Exception(INVALID_PASSWORD_MESSAGE);
        }
        var isBirthDateValid = validateBirthDate(accountPayload.birthDate());
        if(!isBirthDateValid) {
            throw new Exception(INVALID_BIRTHDATE_MESSAGE);
        }
    }

    private boolean validateEmail(String email) {
        var regexPattern = "^(.+)@(\\S+)$";
        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }

    private boolean validatePassword(String password) {
        return password.length() >= 6;
    }

    private boolean validateBirthDate(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears() >= 18;
    }
}
