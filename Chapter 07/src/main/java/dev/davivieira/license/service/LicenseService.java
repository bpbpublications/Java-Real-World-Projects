package dev.davivieira.license.service;

import dev.davivieira.license.entity.License;
import dev.davivieira.license.repository.LicenseRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class LicenseService {

    @Inject
    private LicenseRepository licenseRepository;

    public void createLicense(License license) {
        licenseRepository.persist(license);
    }

    public List<License> getAllLicenses() {
        return licenseRepository.findAllLicenses();
    }
}
