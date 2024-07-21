package dev.davivieira.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    private String id;

    private String name;

    private Double amount;

    private String type;

    private Instant timestamp;
}