import lombok.ToString;

@ToString
public class Client extends User {

    private final static String CLIENT = "CLIENT";
    private final Ticket ticket;

    public Client(Ticket ticket) {
        super(CLIENT);
        this.ticket = ticket;
    }

    @Override
    public long getID() {
        return ID;
    }

    @Override
    public void setID(long ID) {
        this.ID = ID;
    }

    @Override
    public void print() {
        System.out.println(this);
    }
}
