package dev.davivieira.entity;

import dev.davivieira.exception.GenericSpecificationException;
import dev.davivieira.vo.Id;
import dev.davivieira.vo.Type;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    @Test
    public void Given_an_account_create_a_transaction() {
        var account = createAccount("testAccount");
        assertEquals(0, account.transactions().size());

        Transaction.createTransaction(account, "testTransaction", 10.0, Type.CREDIT);

        assertEquals(1, account.transactions().size());
    }

    @Test
    public void Given_an_invalid_transaction_throw_exception() {
        var account = createAccount("testAccount");
        assertThrows(GenericSpecificationException.class, () ->
                Transaction.createTransaction(account, "testTransaction", 0.0, Type.DEBIT));
    }

    @Test
    public void Given_a_category_create_a_credit_transaction() {
        var account = createAccount("testAccount");
        var category = Category.createCategory(account, "testCategory");
        var transaction = Transaction.createTransaction(account, "testTransaction", 10.0, Type.CREDIT);

        assertEquals(0, category.transactions().size());
        transaction.addTransactionToCategory(category);
        assertEquals(1, category.transactions().size());
        transaction.removeTransactionFromCategory(category);
        assertEquals(0, category.transactions().size());
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