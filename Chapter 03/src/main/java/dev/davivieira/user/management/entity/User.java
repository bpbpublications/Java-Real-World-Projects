package dev.davivieira.user.management.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ID", updatable = false, nullable = false)
    @ColumnDefault("random_uuid()")
    private UUID id;
    private String email;
    private String password;
    private String name;

    @ManyToMany
    @JoinTable(
            name="MEMBERSHIP",
            joinColumns = @JoinColumn( name="user_id"),
            inverseJoinColumns = @JoinColumn( name="group_id")
    )
    private List<Group> groups;

    @Override
    public String toString() {
        return "User{" + "email='" + email + '\'' + ", name='" + name + '\'' + ", groups=" + groups + '}';
    }
}
