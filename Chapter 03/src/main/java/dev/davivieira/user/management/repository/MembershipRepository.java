package dev.davivieira.user.management.repository;

import dev.davivieira.user.management.entity.Membership;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;

import java.util.List;

public class MembershipRepository {

    private final Session session;

    public MembershipRepository(EntityManager entityManager) {
        this.session = entityManager.unwrap(Session.class);
    }

    public List<Membership> findAll() {
        return session.createQuery("SELECT m FROM Membership m", Membership.class).getResultList();
    }

    public void persist(Membership membership) {
        session.persist(membership);
        session.flush();
    }

    public void remove(Membership membership) {
        session.remove(membership);
    }
}
