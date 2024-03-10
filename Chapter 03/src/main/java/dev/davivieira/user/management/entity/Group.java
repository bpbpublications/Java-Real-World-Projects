package dev.davivieira.user.management.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "GROUP")
public class Group {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
        @ColumnDefault("random_uuid()")
    private UUID id;
    private String name;

    @ManyToMany(mappedBy = "groups")
    private List<User> users;

    @Override
    public String toString() {
        return STR."Group{name='\{name}'}";
    }
}
