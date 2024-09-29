package hibernate.dao;

import hibernate.model.Ticket;
import ticket.model.TicketType;

public interface TicketDao {
    void save(Ticket ticket);
    Ticket findById(Long id);
    Ticket findByUserId(Long userId);
    void updateType(Ticket ticket,TicketType ticketType);
}
