package database.dao;

import database.model.Ticket;
import ticket.model.TicketType;

import java.util.List;

public interface TicketDao {
    Long save(Ticket ticket);
    Ticket findById(Long ticketId);
    List<Ticket> findByUserId(Long userId);
    Long updateTicketTypeById(Long ticketId, TicketType ticketType);
}