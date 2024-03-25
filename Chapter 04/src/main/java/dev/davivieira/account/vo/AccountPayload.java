package dev.davivieira.account.vo;

import java.time.LocalDate;

public record AccountPayload(String email, String password, LocalDate birthDate) { }
