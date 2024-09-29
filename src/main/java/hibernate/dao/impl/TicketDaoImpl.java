package hibernate.dao.impl;

import hibernate.dao.TicketDao;
import hibernate.model.Ticket;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import ticket.model.TicketType;

@AllArgsConstructor
public class TicketDaoImpl implements TicketDao {

    private static final String FIND_BY_USER_ID = "FROM Ticket t WHERE t.user.id = :userId";
    private static final String UPDATE_TICKET_TYPE = "UPDATE Ticket t SET t.ticketType = :ticketType WHERE t.id = :id";

    private final SessionFactory sessionFactory;

    @Override
    public void save(Ticket ticket) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.persist(ticket);
                session.getTransaction().commit();
            } catch (Exception e) {
                if (session.getTransaction() != null && session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                throw new RuntimeException("Error while saving ticket", e);
            }
        }
    }

    @Override
    public Ticket findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                return session.get(Ticket.class, id);
            } catch (Exception e) {
                if (session.getTransaction() != null && session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                throw new RuntimeException("Error while finding ticket", e);
            }
        }
    }

    @Override
    public Ticket findByUserId(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            try {
                Query<Ticket> query = session.createQuery(FIND_BY_USER_ID, Ticket.class);
                query.setParameter("userId", userId);
                return query.getSingleResult();
            } catch (Exception e) {
                if (session.getTransaction() != null && session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                throw new RuntimeException("Error while finding ticket with user id", e);
            }
        }
    }

    @Override
    public void updateType(Ticket ticket,TicketType ticketType) {
        if (ticket == null || ticket.getId() == null) {
            return;
        }
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();

                MutationQuery query = session.createMutationQuery(UPDATE_TICKET_TYPE);
                query.setParameter("ticketType", ticketType);
                query.setParameter("id", ticket.getId());
                query.executeUpdate();

                session.getTransaction().commit();
            } catch (Exception e) {
                if (session.getTransaction() != null && session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                throw new RuntimeException("Error while updating ticket type", e);
            }
        }
    }
}
