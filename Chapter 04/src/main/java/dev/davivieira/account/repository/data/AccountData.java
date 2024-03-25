package dev.davivieira.account.repository.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "Account")
public class AccountData {

    @Id
    String email;
    String password;
    LocalDate birthDate;
    Instant creationTimestamp;
    String status;

    public AccountData() {
    }

    public AccountData(String email, String password, LocalDate birthDate, Instant creationTimestamp, String status) {
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.creationTimestamp = creationTimestamp;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }

    public String getStatus() {
        return status;
    }


    @Override
    public String toString() {
        return STR."AccountData{email='\{email}\{'\''}, password='\{password}\{'\''}, birthDate=\{birthDate}, creationTimestamp=\{creationTimestamp}, status='\{status}\{'\''}\{'}'}";
    }
}
