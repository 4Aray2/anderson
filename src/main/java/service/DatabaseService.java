package service;

import database.dao.*;
import database.model.Ticket;
import database.model.User;
import ticket.model.TicketType;

public class DatabaseService {

    public void checkUser() {
        UserDao userDao = DaoFactory.createUserDao();
        User user = User.builder()
                .name("marceline")
                .build();
        Long userId = userDao.save(user);
        System.out.println("user saved!");
        System.out.println("found user: " + userDao.findById(userId));

        userDao.deleteById(userId);
        System.out.println("user deleted");

        System.out.println("found user: " + userDao.findById(userId));
    }

    public void checkTicketDefault() {
        TicketDao ticketDao = DaoFactory.createTicketDao();
        UserDao userDao = DaoFactory.createUserDao();
        User user = userDao.findById(2L);

        Ticket dailyTicket = Ticket.builder()
                .ticketType(TicketType.DAY)
                .user(user)
                .build();
        Long dailyTicketId = ticketDao.save(dailyTicket);
        System.out.println("daily ticket saved!");

        System.out.println("find created ticket by id: " + dailyTicketId);
        System.out.println("found ticket: " + ticketDao.findById(dailyTicketId));

        System.out.println("find tickets by user id: " + user.getId());
        System.out.println("found tickets: ");
        for (Ticket ticket : ticketDao.findByUserId(user.getId())) {
            System.out.println("\t" + ticket);
        }

        Long monthlyTicketId = ticketDao.updateTicketTypeById(dailyTicketId, TicketType.MONTH);
        System.out.println("updated ticket type");
        System.out.println("found ticket: " + ticketDao.findById(monthlyTicketId));
    }

    public void checkUserTicketsDelete() {
        UserDao userDao = DaoFactory.createUserDao();
        User user = User.builder()
                .name("marceline")
                .build();
        Long userId = userDao.save(user);
        System.out.println("user saved!");

        System.out.println("found user: " + userDao.findById(userId));


        TicketDao ticketDao = DaoFactory.createTicketDao();
        Ticket dailyTicket = Ticket.builder()
                .ticketType(TicketType.DAY)
                .user(user)
                .build();
        Long dailyTicketId = ticketDao.save(dailyTicket);
        System.out.println("dailyTicket saved!");
        System.out.println("found dailyTicket: " + ticketDao.findById(dailyTicketId));

        Ticket weeklyTicket = Ticket.builder()
                .ticketType(TicketType.WEEK)
                .user(user)
                .build();
        Long weeklyTicketId = ticketDao.save(weeklyTicket);
        System.out.println("weeklyTicket saved!");
        System.out.println("found weeklyTicket: " + ticketDao.findById(weeklyTicketId));

        userDao.deleteById(user.getId());
        System.out.println("user deleted");

        System.out.println("found user: " + userDao.findById(user.getId()));
        System.out.println("found dailyTicket: " + ticketDao.findById(dailyTicket.getId()));
        System.out.println("found weeklyTicket: " + ticketDao.findById(dailyTicket.getId()));
    }
}
