package database.dao;

import entity.TicketType;

import java.util.List;

public interface TicketDao {
    void save(Ticket ticket);
    Ticket findById(Long ticketId);
    List<Ticket> findByUserId(User user);
    void updateTicketTypeById(Long ticketId, TicketType ticketType);
    void deleteById(Long ticketId);
}