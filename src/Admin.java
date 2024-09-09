import lombok.ToString;

import java.util.Set;

@ToString
public class Admin extends User {

    private final static String ADMIN = "ADMIN";
    // smth like database
    private final Set<Ticket> validTickets;

    protected Admin(Set<Ticket> validTickets, int Id) {
        super(ADMIN);
        this.validTickets = validTickets;
        this.Id = Id;
    }

    public boolean checkTicket(Ticket ticket) {
        return validTickets.contains(ticket);
    }

    @Override
    public void print() {
        System.out.println(this);
    }
}
