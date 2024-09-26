package hibernate.dao;

import hibernate.HibernateUtil;
import hibernate.dao.impl.TicketDaoImpl;
import hibernate.dao.impl.UserDaoImpl;
import org.hibernate.SessionFactory;

public class DaoFactory {

    private final SessionFactory sessionFactory;

    public DaoFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserDao getUserDao() {
        return new UserDaoImpl(sessionFactory);
    }

    public TicketDao getTicketDao() {
        return new TicketDaoImpl(sessionFactory);
    }

    public static DaoFactory createFactory() {
        return new DaoFactory(HibernateUtil.getSessionFactory());
    }
}
