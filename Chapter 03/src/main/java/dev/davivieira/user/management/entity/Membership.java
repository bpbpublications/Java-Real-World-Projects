package dev.davivieira.user.management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "MEMBERSHIP")
public class Membership {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Group group;

    public Membership() {

    }

    public Membership(User user, Group group) {
        this.user = user;
        this.group = group;
    }

    @Override
    public String toString() {
        return "Membership{" + "user=" + user + ", group=" + group + '}';
    }
}