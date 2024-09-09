package user;

import lombok.Getter;
import lombok.ToString;
import entity.Ticket;

@Getter
@ToString
public class Client extends User {

    private final static String CLIENT = "CLIENT";
    private final Ticket ticket;

    public Client(Ticket ticket, int Id) {
        super(CLIENT);
        this.ticket = ticket;
        this.Id = Id;
    }

    @Override
    public void print() {
        System.out.println(this);
    }
}
