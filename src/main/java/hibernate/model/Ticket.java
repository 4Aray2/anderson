package hibernate.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ticket.model.TicketType;

import java.time.LocalDate;

@Entity
@Table(name = "ticket")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_generator")
    @SequenceGenerator(name = "ticket_generator", sequenceName = "ticket_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "ticket_type", columnDefinition = "ticket_type")
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.ENUM)
    private TicketType ticketType;

    @Column(name = "creation_date")
    private LocalDate creationDate;
}
