package hibernate;

import hibernate.model.Ticket;
import hibernate.model.User;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    @Getter
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration()
                    .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                    .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/my_ticket_service_db")
                    .setProperty("hibernate.connection.username", "postgres")
                    .setProperty("hibernate.connection.password", "12345")
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
//                    .setProperty("hibernate.show_sql", "true")
                    .setProperty("hibernate.hbm2ddl.auto", "update")
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Ticket.class)
                    .buildSessionFactory();
        } catch (Throwable t) {
            System.err.println("Initial SessionFactory creation failed." + t);
            throw new ExceptionInInitializerError(t);
        }
    }
}