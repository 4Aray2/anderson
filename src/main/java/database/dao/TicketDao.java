package database.dao;

import entity.TicketType;

import java.util.List;

public interface TicketDao {
    Long save(Ticket ticket);
    Ticket findById(Long ticketId);
    List<Ticket> findByUserId(Long userId);
    Long updateTicketTypeById(Long ticketId, TicketType ticketType);
}