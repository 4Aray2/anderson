package database.dao;

public class DaoFactory {

    public static UserDao createUserDao() {
        return new UserDaoImpl();
    }

    public static TicketDao createTicketDao() {
        return new TicketDaoImpl();
    }
}
