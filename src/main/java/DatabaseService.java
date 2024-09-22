import database.dao.*;
import entity.TicketType;

public class DatabaseService {
    public static void main(String[] args) {
        System.out.println("USER TEST");
        checkUser();

        System.out.println();
        System.out.println("TICKET TEST");
        checkTicketDefault();

        System.out.println();
        System.out.println("USER DELETE WITH TICKETS TEST");
        checkTicketUserDelete();
    }

    private static void checkUser() {
        UserDao userDao = DaoFactory.createUserDao();
        User user = User.builder()
                .name("marceline")
                .build();
        userDao.save(user);
        System.out.println("user saved!");

        System.out.println("found user: " + userDao.findById(user.getId()));

        userDao.deleteById(user.getId());
        System.out.println("user deleted");

        System.out.println("found user: " + userDao.findById(user.getId()));
    }

    private static void checkTicketDefault() {
        TicketDao ticketDao = DaoFactory.createTicketDao();
        UserDao userDao = DaoFactory.createUserDao();
        Ticket dailyTicket = Ticket.builder()
                .ticketType(TicketType.DAY)
                .user(userDao.findById(1L))
                .build();
        ticketDao.save(dailyTicket);
        System.out.println("daily ticket saved!");

        System.out.println("found ticket: " + ticketDao.findById(dailyTicket.getId()));

        ticketDao.updateTicketTypeById(dailyTicket.getId(), TicketType.MONTH);
        System.out.println("updated ticket type");
        System.out.println("found ticket: " + ticketDao.findById(dailyTicket.getId()));

        ticketDao.deleteById(dailyTicket.getId());
        System.out.println("ticket deleted");

        System.out.println("found ticket: " + ticketDao.findById(dailyTicket.getId()));
    }

    private static void checkTicketUserDelete() {
        UserDao userDao = DaoFactory.createUserDao();
        User user = User.builder()
                .name("marceline")
                .build();
        userDao.save(user);
        System.out.println("user saved!");

        System.out.println("found user: " + userDao.findById(user.getId()));


        TicketDao ticketDao = DaoFactory.createTicketDao();
        Ticket dailyTicket = Ticket.builder()
                .ticketType(TicketType.DAY)
                .user(user)
                .build();
        ticketDao.save(dailyTicket);
        System.out.println("dailyTicket saved!");

        Ticket weeklyTicket = Ticket.builder()
                .ticketType(TicketType.WEEK)
                .user(user)
                .build();
        ticketDao.save(weeklyTicket);
        System.out.println("weeklyTicket saved!");

        System.out.println("found dailyTicket: " + ticketDao.findById(dailyTicket.getId()));
        System.out.println("found weeklyTicket: " + ticketDao.findById(weeklyTicket.getId()));

        userDao.deleteById(user.getId());
        System.out.println("user deleted");

        System.out.println("found user: " + userDao.findById(user.getId()));
        System.out.println("found dailyTicket: " + ticketDao.findById(dailyTicket.getId()));
        System.out.println("found weeklyTicket: " + ticketDao.findById(dailyTicket.getId()));
    }
}
