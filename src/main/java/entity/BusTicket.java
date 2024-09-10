package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import user.Identifiable;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class BusTicket extends Identifiable {

    private TicketClass ticketClass;
    private TicketType ticketType;
    private LocalDate startDate;
    private Double price;
    private boolean isValid;

    @Override
    public String toString() {
        return "BusTicket{" +
                "Id=" + Id +
                ", isValid=" + isValid +
                ", ticketClass=" + ticketClass +
                ", ticketType=" + ticketType +
                ", startDate=" + startDate +
                ", price=" + price +
                '}';
    }

    enum TicketClass {
        CLA,
        STD
    }

    enum TicketType {
        DAY,
        WEEK,
        MONTH,
        YEAR
    }
}