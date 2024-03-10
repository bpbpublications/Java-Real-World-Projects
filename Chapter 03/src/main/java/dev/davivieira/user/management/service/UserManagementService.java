package dev.davivieira.user.management.service;

import dev.davivieira.user.management.entity.Group;
import dev.davivieira.user.management.entity.Membership;
import dev.davivieira.user.management.entity.User;
import dev.davivieira.user.management.repository.GroupRepository;
import dev.davivieira.user.management.repository.MembershipRepository;
import dev.davivieira.user.management.repository.UserRepository;
import jakarta.persistence.*;
import org.hibernate.Session;

public class UserManagementService {

    @PersistenceContext
    private EntityManager entityManager;

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final MembershipRepository membershipRepository;

    public UserManagementService() {
        setEntityManager();
        this.userRepository = new UserRepository(entityManager);
        this.groupRepository = new GroupRepository(entityManager);
        this.membershipRepository = new MembershipRepository(entityManager);
    }

    public void printAllUsers() {
        System.out.println(userRepository.findAll());
    }

    public void printAllMembership() {
        System.out.println(membershipRepository.findAll());
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Group findGroupByName(String name) {
        return groupRepository.findByName(name);
    }

    public void addUserToGroup(User user, Group group) {
        var membership = new Membership(user,group);
        membershipRepository.persist(membership);
    }

    public void removeUserToGroup(User user, Group group) {
        var membership = new Membership(user,group);
        membershipRepository.remove(membership);
    }


    private void setEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("user");
        this.entityManager = entityManagerFactory.createEntityManager();
    }
}
