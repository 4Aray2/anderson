import java.util.HashSet;
import java.util.Set;

public class TicketService {
    static Ticket emptyTicket;
    static Ticket limitedTicketCustomTime;
    static Ticket limitedTicketDefaultTime;
    static Ticket fullTicketCustomTime;
    static Ticket fullTicketDefaultTime;
    static Ticket fullTicket;

    public static void main(String[] args) {
        generateTickets();
        printTickets();
        share();
        testUsers();
    }

    private static void testUsers() {
        Client goodClient = new Client(fullTicket, 42);
        Client badClient = new Client(emptyTicket, 52);
        Admin admin = new Admin(getTickets(), 1337);

        System.out.printf("Good client Id: %s\n", goodClient.getId());
        System.out.printf("Bad client Id: %s\n", badClient.getId());
        System.out.printf("Admin Id: %s\n", admin.getId());

        if (admin.checkTicket(goodClient.getTicket())) {
            System.out.println("this is GOOD client with REAL ticket");
        }
        if (!admin.checkTicket(badClient.getTicket())) {
            System.out.println("this is BAD client with FAKE ticket");
        }
    }

    private static Set<Ticket> getTickets() {
        Set<Ticket> tickets = new HashSet<>();
        tickets.add(fullTicket);
        tickets.add(fullTicketCustomTime);
        tickets.add(fullTicketDefaultTime);
        return tickets;
    }

    private static void share() {
        fullTicket.shared("fan#1-phone");
        fullTicket.shared("fan#1-phone", "fan#1@gmail.com");
    }

    private static void printTickets() {
        System.out.println("emptyTicket: " + emptyTicket);
        System.out.println("limitedTicketCustomTime: " + limitedTicketCustomTime);
        System.out.println("limitedTicketDefaultTime: " + limitedTicketDefaultTime);
        System.out.println("fullTicketCustomTime: " + fullTicketCustomTime);
        System.out.println("fullTicketDefaultTime: " + fullTicketDefaultTime);
        System.out.println("fullTicket: " + fullTicket);
    }

    private static void generateTickets() {
        String id = "12AB";
        String concertHall = "Main Hall";
        String eventCode = "001";
        long time = System.currentTimeMillis() / 1000L;
        boolean isPromo = false;
        char stadiumSector = 'A';
        double maxAllowedBackpackWeight = 1.337;

        emptyTicket = Ticket.builder().build();
        limitedTicketCustomTime = Ticket.builder()
                .concertHall(concertHall)
                .eventCode(eventCode)
                .build();
        limitedTicketDefaultTime = Ticket.builder()
                .concertHall(concertHall)
                .eventCode(eventCode)
                .time(time)
                .build();
        fullTicketCustomTime = Ticket.builder()
                .id(id)
                .concertHall(concertHall)
                .eventCode(eventCode)
                .isPromo(isPromo)
                .stadiumSector(stadiumSector)
                .maxAllowedBackpackWeight(maxAllowedBackpackWeight)
                .build();
        fullTicketDefaultTime = Ticket.builder()
                .id(id)
                .concertHall(concertHall)
                .eventCode(eventCode)
                .time(time)
                .isPromo(isPromo)
                .stadiumSector(stadiumSector)
                .maxAllowedBackpackWeight(maxAllowedBackpackWeight)
                .build();
        fullTicket = Ticket.builder()
                .id(id)
                .concertHall(concertHall)
                .eventCode(eventCode)
                .time(time)
                .isPromo(isPromo)
                .stadiumSector(stadiumSector)
                .maxAllowedBackpackWeight(maxAllowedBackpackWeight)
                .price(new Price(9.99, "USD"))
                .build();
    }
}
