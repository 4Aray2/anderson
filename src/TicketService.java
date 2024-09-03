import java.util.HashMap;
import java.util.Map;

public class TicketService {

    private static long ticketId = 0;
    private static Map<String, Ticket> tickets;

    public static void main(String[] args) {
        createTickets();
        for (Map.Entry<String, Ticket> entry : tickets.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    private static void createTickets() {
        Map<String, Ticket> ticketMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            Ticket ticket = getNextTicket();
            ticketMap.put(ticket.getID(), ticket);
        }
        tickets = ticketMap;
    }

    private static Ticket getNextTicket() {
        String ID = String.valueOf(ticketId++);
        String concertHall = "Main Hall";
        String eventCode = "001";
        boolean isPromo = false;
        char stadiumSector = 'A';
        double maxAllowedBackpackWeight = 1.337;
        Price price = new Price(9.99, "USD");
        Ticket fullTicket = new Ticket(ID, concertHall, eventCode, isPromo, stadiumSector, maxAllowedBackpackWeight);
        fullTicket.setPrice(price);
        return fullTicket;
    }
}
