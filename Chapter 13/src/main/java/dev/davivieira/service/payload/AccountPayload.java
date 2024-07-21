package dev.davivieira.service.payload;

import lombok.Getter;

import java.util.UUID;

@Getter
public class AccountPayload {

    private String id = UUID.randomUUID().toString();

    private String email;

    private String password;
}
