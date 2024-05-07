package dev.davivieira.license.health;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.Readiness;

@ApplicationScoped
public class LicenseHealthCheck {

    @Produces
    @Liveness
    HealthCheck checkMemoryUsage() {
        return () -> HealthCheckResponse.named("memory-usage").status( true).build();
    }

    @Produces
    @Readiness
    HealthCheck checkCpuUsage() {
        return () -> HealthCheckResponse.named("cpu-usage").status(true).build();
    }
}
