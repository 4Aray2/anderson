import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class Price {

    @NonNull
    private double monetaryAmount;
    @NonNull
    private String currency;
}
