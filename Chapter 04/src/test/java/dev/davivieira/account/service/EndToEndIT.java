package dev.davivieira.account.service;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import dev.davivieira.account.repository.AccountRepository;
import dev.davivieira.account.vo.AccountPayload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static dev.davivieira.account.vo.Status.ACTIVE;
import static dev.davivieira.account.vo.Status.SUSPENDED;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
public class EndToEndIT {

    @Container
    public MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.3.0")
            .withDatabaseName("account")
            .withUsername("test")
            .withPassword("test")
            .withExposedPorts(3306)
            .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
                    new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(3306), new ExposedPort(3306)))
            ));

    private RegistrationService registrationService;

    @BeforeEach
    public void init() {
        registrationService = new RegistrationService(new ValidatorService(), new AccountRepository());
    }

    @Test
    public void givenAnActiveAccountIsProvided_thenAccountIsSuspended() throws Exception {
        // Arrange
        var email = "suspended@daviveira.dev";
        var password = "123456";
        var birthDate = LocalDate.of(2000, 1, 1);

        // Prepare
        var accountPayload = getAccountPayload(email, password, birthDate);
        var activeAccount = registrationService.register(accountPayload);

        // Pre-assert
        assertEquals(activeAccount.status(), ACTIVE);

        // Execute
        var suspendAccount = registrationService.suspend(email);

        // Post-assert
        assertEquals(suspendAccount.status(), SUSPENDED);
    }

    private AccountPayload getAccountPayload(String email, String password, LocalDate birthDate) {
        return new AccountPayload(email, password, birthDate);
    }
}
