package hibernate;

import hibernate.model.Ticket;
import hibernate.model.User;
import hibernate.service.TicketService;
import hibernate.service.UserService;
import ticket.model.TicketType;

public class HibernateTest {
    public void testUserService() {
        UserService userService = new UserService();
        TicketService ticketService = new TicketService();

        User user = User.builder()
                .name("Flash")
                .build();
        System.out.println(user);

        userService.save(user);
        Long id = user.getId();
        System.out.println("user saved with id: " + id);

        User found = userService.findById(id);
        System.out.println("found user: " + found);

        Ticket ticket1 = Ticket.builder()
                .user(user)
                .ticketType(TicketType.DAY)
                .build();
        Ticket ticket2 = Ticket.builder()
                .user(user)
                .ticketType(TicketType.YEAR)
                .build();
        ticketService.save(ticket1);
        ticketService.save(ticket2);
        Long ticketId1 = ticket1.getId();
        Long ticketId2 = ticket2.getId();
        System.out.println("ticket1 saved with id: " + ticketId1);
        System.out.println("ticket2 saved with id: " + ticketId2);

        User deleted = userService.findById(id);
        System.out.println("found user: " + deleted);

        userService.deleteById(id);
        System.out.println("user deleted with id: " + id);

        deleted = userService.findById(id);
        System.out.println("found user: " + deleted);

        Ticket foundTicket1 = ticketService.findById(ticketId1);
        System.out.println("found ticket1: " + foundTicket1);

        Ticket foundTicket2 = ticketService.findById(ticketId2);
        System.out.println("found ticket2: " + foundTicket2);
    }

    public void testTicketService() {
        UserService userService = new UserService();
        TicketService ticketService = new TicketService();

        User user = User.builder()
                .name("Flash")
                .build();
        userService.save(user);
        Long userId = user.getId();
        System.out.println("user saved with id: " + userId);

        Ticket ticket = Ticket.builder()
                .user(user)
                .ticketType(TicketType.DAY)
                .build();
        ticketService.save(ticket);
        Long ticketId = ticket.getId();
        System.out.println("ticket saved with id: " + ticketId);

        ticket = ticketService.findById(ticketId);
        System.out.println("found ticket by id: " + ticket);
        ticket = ticketService.findByUserId(userId);
        System.out.println("found ticket by userId: " + ticket);

        ticketService.updateType(ticket, TicketType.MONTH);
        ticket = ticketService.findById(ticketId);
        System.out.println("found updated ticket by id: " + ticket);
    }
}