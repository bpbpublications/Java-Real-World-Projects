package dev.davivieira.service;

import dev.davivieira.entity.Account;
import dev.davivieira.entity.Category;
import dev.davivieira.entity.Transaction;
import dev.davivieira.vo.Type;

import java.util.List;

public class TransactionService {

    private final Account account;

    public TransactionService(Account account) {
        this.account = account;
    }

    public List<Transaction> findTransactionsByType(Type type) {
        return account
                .transactions()
                .stream()
                .filter(transaction -> transaction.type().equals(type))
                .toList();
    }

    public List<Transaction> findTransactionsByCategory(Category category) {
        var foundCategory = account.categories().stream()
                .filter(accountCategory -> accountCategory.equals(category))
                .findAny()
                .orElse(null);

        if(foundCategory!=null) {
            return foundCategory.transactions();
        } else {
            return List.of();
        }
    }
}
