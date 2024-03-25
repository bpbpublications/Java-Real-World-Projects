package dev.davivieira.account.entity;

import dev.davivieira.account.vo.Status;

import java.time.Instant;
import java.time.LocalDate;

public record Account(
        String email,
        String password,
        LocalDate birthDate,
        Instant creationTimestamp,
        Status status)
{ }
