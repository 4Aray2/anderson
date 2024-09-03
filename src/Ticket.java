public class Ticket {
    private String ID;
    private String concertHall;
    private String eventCode;
    private long time;
    private boolean isPromo;
    private char stadiumSector;
    private double maxAllowedBackpackWeight;

    private Price price;

    // empty
    public Ticket() {}

    // limited
    // when time automatically created
    public Ticket(String concertHall, String eventCode) {
        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.time = getUnixTimeNow();
    }

    // limited
    // when time manually created
    public Ticket(String concertHall, String eventCode, long time) {
        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.time = time;
    }

    // full
    // when time automatically created
    public Ticket(String ID, String concertHall, String eventCode,
                  boolean isPromo, char stadiumSector, double maxAllowedBackpackWeight) {
        this.ID = ID;
        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.time = getUnixTimeNow();
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.maxAllowedBackpackWeight = maxAllowedBackpackWeight;
    }

    // full
    // when time manually created
    public Ticket(String ID, String concertHall, String eventCode, long time,
                  boolean isPromo, char stadiumSector, double maxAllowedBackpackWeight) {
        this.ID = ID;
        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.time = time;
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.maxAllowedBackpackWeight = maxAllowedBackpackWeight;
    }

    public String getID() {
        return ID;
    }

    private long getUnixTimeNow() {
        return System.currentTimeMillis() / 1000L;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ID=" + ID +
                ", concertHall='" + concertHall + '\'' +
                ", eventCode='" + eventCode + '\'' +
                ", time=" + time +
                ", isPromo=" + isPromo +
                ", stadiumSector=" + stadiumSector +
                ", maxAllowedBackpackWeight=" + maxAllowedBackpackWeight +
                ", price=" + price +
                '}';
    }
}
