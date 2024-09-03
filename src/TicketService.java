import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TicketService {

    private static byte ticketId = 0;
    private static Map<String, Ticket> tickets;

    public static void main(String[] args) {
        createTickets();
        String id = "1";
        Ticket foundTicket = findTicketById(id);
        System.out.println("Found ticket: " + foundTicket);
        printTicketsByStadium('B');
    }

    private static Ticket findTicketById(String id) {
        return tickets.get(id);
    }

    private static void printTicketsByStadium(char stadiumSector){

        for (Ticket ticket : getTicketsByStadium(stadiumSector)){
            System.out.println("Founded ticket: " + ticket.toString());
        }
    }
    private static ArrayList<Ticket> getTicketsByStadium(char stadiumSector){

        ArrayList<Ticket> foundedTickets = new ArrayList<>();

        for (Ticket ticket : tickets.values()){
            if(ticket.getStadiumSector() == stadiumSector) {
                foundedTickets.add(ticket);
            }
        }

        return foundedTickets;
    }

    private static void createTickets() {
        Map<String, Ticket> ticketMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            Ticket ticket = getNextTicket();
            ticketMap.put(ticket.getId(), ticket);
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
