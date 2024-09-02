public class TicketService {

    public static void main(String[] args) {
        String ID = "12AB";
        String concertHall = "Main Hall";
        String eventCode = "001";
        long time = System.currentTimeMillis() / 1000L;
        boolean isPromo = false;
        char stadiumSector = 'A';
        double maxAllowedBackpackWeight = 1.337;
        
        Ticket emptyTicket = new Ticket();
        Ticket limitedTicket = new Ticket(concertHall, eventCode);
        Ticket limitedTicketTime = new Ticket(concertHall, eventCode, time);
        Ticket fullTicket = new Ticket(ID, concertHall, eventCode, isPromo, stadiumSector, maxAllowedBackpackWeight);
        Ticket fullTicketTime = new Ticket(ID, concertHall, eventCode, time, isPromo, stadiumSector, maxAllowedBackpackWeight);

        System.out.println("emptyTicket: " + emptyTicket);
        System.out.println("limitedTicket: " + limitedTicket);
        System.out.println("limitedTicketTime: " + limitedTicketTime);
        System.out.println("fullTicket: " + fullTicket);
        System.out.println("fullTicketTime: " + fullTicketTime);


        Price price = new Price(9.99, "USD");
        fullTicket.setPrice(price);
        System.out.println("fullTicketTime with price: " + fullTicket);
    }
}
