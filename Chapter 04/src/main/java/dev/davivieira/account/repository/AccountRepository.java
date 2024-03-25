package dev.davivieira.account.repository;

import dev.davivieira.account.entity.Account;
import dev.davivieira.account.repository.data.AccountData;
import dev.davivieira.account.vo.Status;
import jakarta.persistence.*;

public class AccountRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public AccountRepository() {
        setEntityManager();
    }

    public void persist(Account account) {
        var accountData = convertEntityToData(account);
        entityManager.merge(accountData);
        entityManager.flush();
    }

    public Account findByEmail(String email) {
        Query query = entityManager.createQuery("SELECT a FROM AccountData a WHERE email = :email", AccountData.class);
        query.setParameter("email", email);
        var accountData = (AccountData) query.getSingleResult();
        return convertDataToEntity(accountData);
    }

    private void setEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("account");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public AccountData convertEntityToData(Account account){
        return new AccountData(
                account.email(),
                account.password(),
                account.birthDate(),
                account.creationTimestamp(),
                account.status().name()
        );
    }

    public Account convertDataToEntity(AccountData accountData){
        return new Account(
                accountData.getEmail(),
                accountData.getPassword(),
                accountData.getBirthDate(),
                accountData.getCreationTimestamp(),
                Status.valueOf(accountData.getStatus())
        );
    }
}
