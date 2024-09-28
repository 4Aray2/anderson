import service.BusTicketService;
import service.CollectionService;
import service.DatabaseService;
import service.TicketService;
import ticket.StatisticsHelper;

public class Test {
    public static void main(String[] args) {
        testTicket();
        testBusTicket();
        testCollections();
        testPlainJdbc();
        testHibernate();
    }

    private static void testTicket() {
        TicketService ticketService = new TicketService();
        ticketService.generateTickets();
        ticketService.printTickets();
        ticketService.share();
        ticketService.testUsers();
    }

    private static void testBusTicket() {
        StatisticsHelper statisticsHelper = new StatisticsHelper();

        BusTicketService busTicketService = new BusTicketService();
        busTicketService.checkTickets(statisticsHelper);

        statisticsHelper.print();
    }

    private static void testCollections() {
        CollectionService collectionService = new CollectionService();
        collectionService.arrayTest();
        collectionService.setTest();
    }

    private static void testPlainJdbc() {
        DatabaseService databaseService = new DatabaseService();
        System.out.println("JDBC USER TEST");
        databaseService.checkUser();

        System.out.println();
        System.out.println("JDBC TICKET TEST");
        databaseService.checkTicketDefault();

        System.out.println();
        System.out.println("JDBC USER DELETE WITH TICKETS TEST");
        databaseService.checkUserTicketsDelete();
    }

    private static void testHibernate() {
        hibernate.Test test = new hibernate.Test();

        System.out.println("HIBERNATE USER TEST");
        test.testUserService();

        System.out.println();
        System.out.println("HIBERNATE TICKET TEST");
        test.testTicketService();
    }
}
