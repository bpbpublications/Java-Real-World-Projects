package dev.davivieira.service;

import dev.davivieira.entity.Account;
import dev.davivieira.entity.Category;
import dev.davivieira.entity.Transaction;
import dev.davivieira.vo.Id;
import dev.davivieira.vo.Type;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionServiceTest {

    @Test
    public void Given_an_account_create_a_transaction() {
        var account = createAccount("testAccount");
        var category = Category.createCategory(account, "testCategory");
        var transaction = Transaction.createTransaction(account, "testTransaction", 10.0, Type.CREDIT);
        var transactionService = new TransactionService(account);

        transaction.addTransactionToCategory(category);

        assertEquals(transaction, transactionService.findTransactionsByCategory(category).getFirst());
        assertEquals(transaction, transactionService.findTransactionsByType(Type.CREDIT).getFirst());
    }

    private Account createAccount(String name) {
        return Account
                .builder()
                .id(Id.withoutId())
                .name(name)
                .transactions(new ArrayList<>())
                .categories(new ArrayList<>())
                .build();
    }
}
