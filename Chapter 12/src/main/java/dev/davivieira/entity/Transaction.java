package dev.davivieira.entity;

import dev.davivieira.specification.TransactionAmountSpec;
import dev.davivieira.vo.Id;
import dev.davivieira.vo.Type;
import lombok.Builder;

import java.time.Instant;

@Builder
public record Transaction (Id id, String name, Double amount, Type type, Instant timestamp) {

    public static Transaction createTransaction(Account account, String name, Double amount, Type type) {
        var transaction = getTransaction(name, amount, type);
        var transactions = account.transactions();

        new TransactionAmountSpec().check(transaction.amount);

        transactions.add(transaction);

        return transaction;
    }

    private static Transaction getTransaction(String name, Double amount, Type type) {
        return Transaction.builder()
                .id(Id.withoutId())
                .name(name)
                .amount(amount)
                .type(type)
                .timestamp(Instant.now())
                .build();
    }

    public boolean addTransactionToCategory(Category category) {
        return category.transactions().add(this);
    }

    public boolean removeTransactionFromCategory(Category category) {
        return category.transactions().remove(this);
    }
}
