package database.dao;

import database.dao.impl.TicketDaoImpl;
import database.dao.impl.UserDaoImpl;

public class DaoFactory {

    public static UserDao createUserDao() {
        return new UserDaoImpl();
    }

    public static TicketDao createTicketDao() {
        return new TicketDaoImpl();
    }
}
