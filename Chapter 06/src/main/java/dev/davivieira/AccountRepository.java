package dev.davivieira;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccountRepository implements PanacheRepository<Account> {

    public Account findByEmail(String email) {
        return find("email", email).firstResult();
    }

    public void deleteByEmail(String email) {
        delete("email", email);
    }
}
