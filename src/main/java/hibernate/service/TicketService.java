package hibernate.service;

import hibernate.dao.DaoFactory;
import hibernate.dao.TicketDao;
import hibernate.model.Ticket;
import ticket.model.TicketType;

public class TicketService {

    private final TicketDao ticketDao;

    public TicketService() {
        this.ticketDao = DaoFactory.createFactory().getTicketDao();
    }


    public void save(Ticket ticket) {
        ticketDao.save(ticket);
    }

    public Ticket findById(Long id) {
        return ticketDao.findById(id);
    }

    public Ticket findByUserId(Long userId) {
        return ticketDao.findByUserId(userId);
    }

    public void updateType(Ticket ticket, TicketType ticketType) {
        ticketDao.updateType(ticket, ticketType);
    }
}
