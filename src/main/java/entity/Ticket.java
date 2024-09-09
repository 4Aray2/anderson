package entity;

import annotation.NullableWarning;
import annotation.NullableWarningChecker;
import lombok.*;

@Builder
@ToString
@Getter
@EqualsAndHashCode
public class Ticket {

    @NullableWarning
    private final String id;

    @NullableWarning
    private final String concertHall;

    @NullableWarning
    private final String eventCode;

    @NullableWarning
    private final boolean isPromo;

    @NullableWarning
    private final double maxAllowedBackpackWeight;

    @NullableWarning
    private final Price price;

    @NullableWarning
    @Builder.Default
    @Setter
    private long time = System.currentTimeMillis() / 1000L;

    @NullableWarning
    @Setter
    private char stadiumSector;

    {
        NullableWarningChecker.checkForNulls(this);
    }

    public void shared(String phone) {
        System.out.printf("Ticket shared with phone: %s\n", phone);
    }

    public void shared(String phone, String email) {
        System.out.printf("Ticket shared with phone: %s and email: %s\n", phone, email);
    }
}
