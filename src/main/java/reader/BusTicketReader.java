package reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import entity.BusTicket;
import entity.Statistics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BusTicketReader {

    public static List<BusTicket> readBusTickets(String filePath, Statistics statistics) {
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

        System.out.println("Successfully read " + busTickets.size() + " tickets from file " + filePath + ".");
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
                statistics.update(busTicket);
                busTickets.add(busTicket);
            } catch (JsonSyntaxException e) {
                System.err.println("Error parsing JSON at line " + lineNumber + " line");
            }
        }
        statistics.updateMostPopularViolation();
        return busTickets;
    }
}
