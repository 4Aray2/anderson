package database.model;

import ticket.model.TicketType;
import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class Ticket {

    @Setter
    private Long id;
    private User user;
    private TicketType ticketType;
    private LocalDate creationDate;
}
