package dev.davivieira;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import static java.lang.StringTemplate.STR;

@Entity
public class Account {

    @Id
    private String email;

    private String password;

    public Account() {

    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return STR."Account{email='\{email}\{'\''}, password='\{password}\{'\''}\{'}'}";
    }
}
