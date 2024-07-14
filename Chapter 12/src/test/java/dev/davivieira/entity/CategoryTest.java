package dev.davivieira.entity;

import dev.davivieira.exception.GenericSpecificationException;
import dev.davivieira.vo.Id;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    @Test
    public void Given_an_account_exists_create_a_category() {
        var name = "testAccount";
        var category = "testCategory";
        var account = createAccount(name);
        assertEquals(0, account.categories().size());

        Category.createCategory(account, category);

        assertEquals(1, account.categories().size());
    }

    @Test
    public void Given_a_category_already_exists_throw_exception() {
        var name = "testAccount";
        var category = "testCategory";
        var account = createAccount(name);
        Category.createCategory(account, category);
        assertThrows(GenericSpecificationException.class, () -> Category.createCategory(account, category));
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
