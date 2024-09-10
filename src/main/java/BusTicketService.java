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
        List<BusTicket> busTickets = BusTicketReader.readBusTickets(filePath, statistics);
        for (BusTicket busTicket : busTickets) {
            System.out.println(busTicket);
        }
    }
}
