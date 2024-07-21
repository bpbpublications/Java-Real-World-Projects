package dev.davivieira.service.payload;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class TransactionPayload {

    private String id = UUID.randomUUID().toString();

    private String accountId;

    private String name;

    private Double amount;

    private String type;

    private Instant timestamp = Instant.now();
}
