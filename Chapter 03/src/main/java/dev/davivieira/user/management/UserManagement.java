package dev.davivieira.user.management;

import dev.davivieira.user.management.service.UserManagementService;

public class UserManagement {

    public static void main(String... args) {
        var userManagementService = new UserManagementService();
        var user = userManagementService.findUserByEmail("user1@davivieira.dev");
        var group = userManagementService.findGroupByName("Administrators");
        userManagementService.addUserToGroup(user, group);
        userManagementService.printAllUsers();
        userManagementService.printAllMembership();
    }

}
