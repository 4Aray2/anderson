package entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import user.Printable;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Stream;

@ToString
@Setter
@Getter
public class Statistics implements Printable {

    private int total;
    private int valid;
    private ViolationType mostPopularViolation;
    private int startDateViolationCount;
    private int priceViolationCount;
    private int ticketTypeViolationCount;

    public void updateTotal() {
        total++;
    }

    public void updateValid() {
        valid++;
    }

    public void updateMostPopularViolation() {
        this.mostPopularViolation = Stream.of(
                        new AbstractMap.SimpleEntry<>(ViolationType.START_DATE, startDateViolationCount),
                        new AbstractMap.SimpleEntry<>(ViolationType.PRICE, priceViolationCount),
                        new AbstractMap.SimpleEntry<>(ViolationType.TICKET_TYPE, ticketTypeViolationCount)
                )
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public void updateTicketTypeViolationCount() {
        ticketTypeViolationCount++;
    }

    public void updatePriceViolationCount() {
        priceViolationCount++;
    }

    public void updateStartDateViolationCount() {
        startDateViolationCount++;
    }

    @Override
    public void print() {
        System.out.println("...\n" +
                "Total = " + total + "\n" +
                "Valid = " + valid + "\n" +
                "Most popular violation = " + mostPopularViolation + "\n" +
                "...");
    }

    enum ViolationType {
        START_DATE,
        PRICE,
        TICKET_TYPE
    }
}


