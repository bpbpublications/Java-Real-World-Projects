package dev.davivieira.license.repository;

import dev.davivieira.license.entity.License;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class LicenseRepository {

    @PersistenceContext
    private EntityManager em;

    public void persist(License license) {
        em.persist(license);
    }

    public List<License> findAllLicenses() {
        return (List<License>) em
                .createQuery("SELECT license from License license")
                .getResultList();
    }
}
