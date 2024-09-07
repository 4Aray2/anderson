import lombok.ToString;

@ToString
public class Admin extends User {

    protected Admin(String role) {
        super(role);
    }

    public boolean checkTicket(Ticket ticket) {
        return Math.random() < 0.5;
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
