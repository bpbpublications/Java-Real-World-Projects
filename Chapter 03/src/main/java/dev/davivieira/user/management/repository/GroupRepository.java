package dev.davivieira.user.management.repository;

import dev.davivieira.user.management.entity.Group;
import dev.davivieira.user.management.entity.User;
import jakarta.persistence.*;
import org.hibernate.Session;

import java.util.List;

public class GroupRepository {

    private final Session session;

    public GroupRepository(EntityManager entityManager) {
        this.session = entityManager.unwrap(Session.class);
    }

    public List<Group> findAll() {
        return session.createQuery("SELECT g FROM Group g", Group.class).getResultList();
    }

    private void persist(Group group) {
        session.persist(group);
    }

    public Group findByName(String name) {
        Query query = session.createQuery("SELECT g FROM Group g WHERE name = :name", Group.class);
        query.setParameter("name", name);
        return (Group) query.getSingleResult();
    }
}
