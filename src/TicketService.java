public class TicketService {

    public static void main(String[] args) {
        String id = "12AB";
        String concertHall = "Main Hall";
        String eventCode = "001";
        long time = System.currentTimeMillis() / 1000L;
        boolean isPromo = false;
        char stadiumSector = 'A';
        double maxAllowedBackpackWeight = 1.337;

        Ticket emptyTicket = Ticket.builder().build();
        Ticket limitedTicketCustomTime = Ticket.builder()
                .concertHall(concertHall)
                .eventCode(eventCode)
                .build();
        Ticket limitedTicketDefaultTime = Ticket.builder()
                .concertHall(concertHall)
                .eventCode(eventCode)
                .time(time)
                .build();
        Ticket fullTicketCustomTime = Ticket.builder()
                .id(id)
                .concertHall(concertHall)
                .eventCode(eventCode)
                .isPromo(isPromo)
                .stadiumSector(stadiumSector)
                .maxAllowedBackpackWeight(maxAllowedBackpackWeight)
                .build();
        Ticket fullTicketDefaultTime = Ticket.builder()
                .id(id)
                .concertHall(concertHall)
                .eventCode(eventCode)
                .time(time)
                .isPromo(isPromo)
                .stadiumSector(stadiumSector)
                .maxAllowedBackpackWeight(maxAllowedBackpackWeight)
                .build();
        Ticket fullTicket = Ticket.builder()
                .id(id)
                .concertHall(concertHall)
                .eventCode(eventCode)
                .time(time)
                .isPromo(isPromo)
                .stadiumSector(stadiumSector)
                .maxAllowedBackpackWeight(maxAllowedBackpackWeight)
                .price(new Price(9.99, "USD"))
                .build();

        System.out.println("emptyTicket: " + emptyTicket);
        System.out.println("limitedTicketCustomTime: " + limitedTicketCustomTime);
        System.out.println("limitedTicketDefaultTime: " + limitedTicketDefaultTime);
        System.out.println("fullTicketCustomTime: " + fullTicketCustomTime);
        System.out.println("fullTicketDefaultTime: " + fullTicketDefaultTime);
        System.out.println("fullTicket: " + fullTicket);

        fullTicket.shared("fan#1-phone");
        fullTicket.shared("fan#1-phone", "fan#1@gmail.com");
    }
}
