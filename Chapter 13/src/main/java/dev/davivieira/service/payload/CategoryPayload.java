package dev.davivieira.service.payload;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CategoryPayload {

    private String id = UUID.randomUUID().toString();

    private String accountId;

    private String name;
}
