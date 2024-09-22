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
@ToString
public class BusTicket extends Identifiable {

    private TicketClass ticketClass;
    private TicketType ticketType;
    private LocalDate startDate;
    private Double price;
    private boolean isValid;
}