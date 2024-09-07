import lombok.*;

@Builder
@ToString
@Getter
@EqualsAndHashCode
public class Ticket {

    private final String id;
    private final String concertHall;
    private final String eventCode;
    private final boolean isPromo;
    private final double maxAllowedBackpackWeight;
    private final Price price;

    @Builder.Default
    @Setter
    private long time = System.currentTimeMillis() / 1000L;

    @Setter
    private char stadiumSector;

    public void shared(String phone) {
        System.out.printf("Ticket shared with phone: %s\n", phone);
    }

    public void shared(String phone, String email) {
        System.out.printf("Ticket shared with phone: %s and email: %s\n", phone, email);
    }
}
