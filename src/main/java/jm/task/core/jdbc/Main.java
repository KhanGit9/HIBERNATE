package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Apti", "Chapaev", (byte) 21);
        userService.saveUser("Usman", "Kaedanov", (byte) 19);
        userService.saveUser("Ukumba", "Makumba", (byte) 33);
        userService.removeUserById(1);
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
