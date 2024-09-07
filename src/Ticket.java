import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class Ticket implements Identifiable {

    private long id;
    private final String concertHall;
    private final String eventCode;
    @Builder.Default
    private final long time = getUnixTimeNow();
    private final boolean isPromo;
    private final char stadiumSector;
    private final double maxAllowedBackpackWeight;
    private final Price price;

    private long getUnixTimeNow() {
        return System.currentTimeMillis() / 1000L;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }
}
