package service;

import ticket.model.BusTicket;
import ticket.StatisticsHelper;
import reader.BusTicketReader;

import java.util.List;

public class BusTicketService {

    private final static String filePath = "src/main/resources/ticketsToCheck.txt";

    public void checkTickets(StatisticsHelper statisticsHelper) {
        List<BusTicket> busTickets = BusTicketReader.readBusTickets(filePath, statisticsHelper);
        for (BusTicket busTicket : busTickets) {
            System.out.println(busTicket);
        }
    }
}
