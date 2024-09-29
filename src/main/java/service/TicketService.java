package service;

import ticket.model.Price;
import ticket.model.Ticket;
import user.Admin;
import user.Client;

import java.util.HashSet;
import java.util.Set;

public class TicketService {

    private static Ticket emptyTicket;
    private static Ticket limitedTicketCustomTime;
    private static Ticket limitedTicketDefaultTime;
    private static Ticket fullTicketCustomTime;
    private static Ticket fullTicketDefaultTime;
    private static Ticket fullTicket;

    public void testUsers() {
        Client goodClient = new Client(fullTicket, 42);
        Client badClient = new Client(emptyTicket, 52);
        Admin admin = new Admin(getTickets(), 1337);

        badClient.print();
        goodClient.print();
        admin.print();

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

    public void share() {
        fullTicket.shared("fan#1-phone");
        fullTicket.shared("fan#1-phone", "fan#1@gmail.com");
    }

    public void printTickets() {
        System.out.println("emptyTicket: " + emptyTicket);
        System.out.println("limitedTicketCustomTime: " + limitedTicketCustomTime);
        System.out.println("limitedTicketDefaultTime: " + limitedTicketDefaultTime);
        System.out.println("fullTicketCustomTime: " + fullTicketCustomTime);
        System.out.println("fullTicketDefaultTime: " + fullTicketDefaultTime);
        System.out.println("fullTicket: " + fullTicket);
    }

    public void generateTickets() {
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

    private Set<Ticket> getTickets() {
        Set<Ticket> tickets = new HashSet<>();
        tickets.add(fullTicket);
        tickets.add(fullTicketCustomTime);
        tickets.add(fullTicketDefaultTime);
        return tickets;
    }
}
