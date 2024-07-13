package dev.davivieira.entity;

import dev.davivieira.vo.Id;
import lombok.Builder;

import java.util.List;

public record Account(Id id, String name, List<Transaction> transactions, List<Category> categories) {

    @Builder
    public Account(Id id, String name, List<Transaction> transactions, List<Category> categories) {

        this.id = id;
        this.name = name;

        if (transactions == null) {
            throw new RuntimeException("Transaction list cannot be null");
        } else {
            this.transactions = transactions;
        }

        if (categories == null) {
            throw new RuntimeException("Categories list cannot be null");
        } else {
            this.categories = categories;
        }
    }
}