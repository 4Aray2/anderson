package hibernate;

import hibernate.model.User;
import hibernate.service.TicketService;
import hibernate.service.UserService;

public class Main {
    public static void main(String[] args) {
        testUserService();
        testTicketService();
    }

    private static void testUserService() {
        UserService userService = new UserService();

        User user = User.builder()
                .name("Flash")
                .build();
        System.out.println(user);

        userService.save(user);
        Long id = user.getId();
        System.out.println("user saved with id: " + id);

        User found = userService.findById(id);
        System.out.println("found user: " + found);

        userService.deleteById(id);
        System.out.println("user deleted with id: " + id);


        User deleted = userService.findById(id);
        System.out.println("found user: " + deleted);
    }

    private static void testTicketService() {
        TicketService ticketService = new TicketService();

    }
}