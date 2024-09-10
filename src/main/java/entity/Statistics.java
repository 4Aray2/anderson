package entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import user.Printable;

import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
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

    @Override
    public void print() {
        System.out.println("...\n" +
                "Total = " + total + "\n" +
                "Valid = " + valid + "\n" +
                "Most popular violation = " + mostPopularViolation + "\n" +
                "...");
    }

    public void update(BusTicket busTicket) {
        total++;
        busTicket.setId(total);
        boolean hasViolation = false;

        if (Objects.isNull(busTicket.getTicketType())) {
            ticketTypeViolationCount++;
            hasViolation = true;
        }

        if (Objects.isNull(busTicket.getPrice())
                || busTicket.getPrice() <= 0
                || busTicket.getPrice() % 2 == 1) {
            priceViolationCount++;
            hasViolation = true;
        }

        if (Objects.isNull(busTicket.getStartDate())
                || busTicket.getStartDate().isAfter(LocalDate.now())) {
            startDateViolationCount++;
            hasViolation = true;
        }

        if (!hasViolation && Objects.nonNull(busTicket.getTicketClass())) {
            valid++;
            busTicket.setValid(true);
        }
    }

    enum ViolationType {
        START_DATE,
        PRICE,
        TICKET_TYPE
    }
}


