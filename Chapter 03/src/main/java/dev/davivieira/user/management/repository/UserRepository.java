package dev.davivieira.user.management.repository;

import dev.davivieira.user.management.entity.User;
import jakarta.persistence.*;
import org.hibernate.Session;

import java.util.List;

public class UserRepository {

    private final Session session;

    public UserRepository(EntityManager entityManager) {
        this.session = entityManager.unwrap(Session.class);
    }

    public List<User> findAll() {
        return session.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public User findByEmail(String email) {
        Query query = session.createQuery("SELECT u FROM User u WHERE email = :email", User.class);
        query.setParameter("email", email);
        return (User) query.getSingleResult();
    }

    private void persist(User user) {
        session.persist(user);
    }
}
