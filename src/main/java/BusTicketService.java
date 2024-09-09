import com.google.gson.*;
import entity.BusTicket;
import entity.Statistics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BusTicketService {

    private final static String filePath = "src/main/resources/ticketsToCheck.txt";

    public static void main(String[] args) {
        Statistics statistics = new Statistics();
        checkTickets(statistics);

        statistics.print();
        System.out.println(statistics);
    }

    private static void checkTickets(Statistics statistics) {
        List<BusTicket> busTickets = readBusTicketsFromFile(statistics);
        for (BusTicket busTicket : busTickets) {
            System.out.println(busTicket);
        }
    }

    private static List<BusTicket> readBusTicketsFromFile(Statistics statistics) {
        List<BusTicket> busTickets = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        if (!Files.exists(Paths.get(filePath))) {
            System.err.println("File does not exist: " + filePath);
            return busTickets;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            busTickets = parseBusTickets(gson, reader, statistics);
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
        }

        System.out.println("Successfully read " + busTickets.size() + " tickets from file.");
        return busTickets;
    }

    private static List<BusTicket> parseBusTickets(Gson gson, BufferedReader reader, Statistics statistics) throws IOException {
        List<BusTicket> busTickets = new ArrayList<>();
        String line;
        int lineNumber = 0;
        while ((line = reader.readLine()) != null) {
            lineNumber++;
            try {
                BusTicket busTicket = gson.fromJson(line, BusTicket.class);
                updateStatistics(busTicket, statistics);
                busTickets.add(busTicket);
            } catch (JsonSyntaxException e) {
                System.err.println("Error parsing JSON at line " + lineNumber + " line");
            }
        }
        statistics.updateMostPopularViolation();
        return busTickets;
    }

    private static void updateStatistics(BusTicket busTicket, Statistics statistics) {
        statistics.updateTotal();
        busTicket.setId(statistics.getTotal());
        boolean hasViolation = false;

        if (Objects.isNull(busTicket.getTicketType())) {
            statistics.updateTicketTypeViolationCount();
            hasViolation = true;
        }

        if (Objects.isNull(busTicket.getPrice())
                || busTicket.getPrice() <= 0
                || busTicket.getPrice() % 2 == 1) {
            statistics.updatePriceViolationCount();
            hasViolation = true;
        }

        if (Objects.isNull(busTicket.getStartDate())
                || busTicket.getStartDate().isAfter(LocalDate.now())) {
            statistics.updateStartDateViolationCount();
            hasViolation = true;
        }

        if (!hasViolation && Objects.nonNull(busTicket.getTicketClass())) {
            statistics.updateValid();
            busTicket.setValid(true);
        }
    }

    private static class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
        @Override
        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
            return date == null ? JsonNull.INSTANCE : new JsonPrimitive(date.toString());
        }

        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            if (json.isJsonNull() || json.getAsString().isEmpty()) {
                return null;
            }
            try {
                return LocalDate.parse(json.getAsString());
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing date: " + json.getAsString());
                return null;
            }
        }
    }
}
