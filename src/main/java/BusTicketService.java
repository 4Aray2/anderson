import entity.BusTicket;
import entity.Statistics;
import reader.BusTicketReader;

import java.util.List;

public class BusTicketService {

    private final static String filePath = "src/main/resources/ticketsToCheck.txt";

    public static void main(String[] args) {
        Statistics statistics = new Statistics();
        checkTickets(statistics);

        statistics.print();
        System.out.println(statistics);
    }

    private static void checkTickets(Statistics statistics) {
        List<BusTicket> busTickets = BusTicketReader.readBusTickets(filePath, statistics);
        for (BusTicket busTicket : busTickets) {
            System.out.println(busTicket);
        }
    }
}
