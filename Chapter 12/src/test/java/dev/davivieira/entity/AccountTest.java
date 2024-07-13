package dev.davivieira.entity;

import dev.davivieira.exception.GenericSpecificationException;
import dev.davivieira.vo.Id;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    public void accountIsSuccessfullyCreated() {
        var name = "testAccount";
        var account = createAccount(name);
        assertEquals(name, account.name());
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
