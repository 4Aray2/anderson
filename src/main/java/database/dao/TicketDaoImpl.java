package database.dao;

import database.DataBaseException;
import database.DatabaseConnectionManager;
import entity.TicketType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TicketDaoImpl implements TicketDao {

    private static final String ID = "id";
    private static final String USER_ID = "user_id";
    private static final String TICKET_TYPE = "ticket_type";
    private static final String CREATION_DATE = "creation_date";
    private static final String NAME = "name";
    private static final String USER_CREATION_DATE = "user_creation_date";

    @Override
    public void save(Ticket ticket) {
        String sql = "INSERT INTO ticket(user_id, ticket_type) VALUES (?, ?::ticket_type)";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, ticket.getUser().getId());
            ps.setString(2, ticket.getTicketType().name());
            int rowsAffected = ps.executeUpdate();

            if(rowsAffected > 0) {
                ResultSet result = ps.getGeneratedKeys();
                if(result.next()) {
                    long id = result.getLong(ID);
                    ticket.setId(id);
                }
            } else {
                throw new DataBaseException("did not saved properly!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Ticket findById(Long id) {
        String sql = "SELECT t.*, u.\"name\", u.creation_date AS user_creation_date " +
                "FROM ticket t LEFT JOIN \"user\" u ON t.user_id = u.id WHERE t.id = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Ticket.builder()
                        .id(rs.getLong(ID))
                        .user(getUser(rs))
                        .ticketType(TicketType.valueOf(rs.getString(TICKET_TYPE)))
                        .creationDate(rs.getObject(CREATION_DATE, LocalDate.class))
                        .build();

            }
            return null;
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    private User getUser(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getLong(USER_ID))
                .name(rs.getString(NAME))
                .createdDate(rs.getObject(USER_CREATION_DATE, LocalDate.class))
                .build();
    }

    @Override
    public List<Ticket> findByUserId(User user) {
        String sql = "SELECT t.*, u.\"name\", u.creation_date AS user_creation_date " +
                "FROM ticket t LEFT JOIN \"user\" u ON t.user_id = u.id WHERE user_id = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, user.getId());
            ResultSet rs = ps.executeQuery();

            List<Ticket> tickets = new ArrayList<>();

            while (rs.next()) {
                tickets.add(Ticket.builder()
                        .id(rs.getLong(ID))
                        .user(getUser(rs))
                        .ticketType(TicketType.valueOf(rs.getString(TICKET_TYPE)))
                        .creationDate(rs.getObject(CREATION_DATE, LocalDate.class))
                        .build());
            }

            return tickets;
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    @Override
    public void updateTicketTypeById(Long ticketId, TicketType ticketType) {

        String sql = "UPDATE ticket SET ticket_type=?::ticket_type WHERE id=?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, ticketType.name());
            ps.setLong(2, ticketId);
            ps.executeUpdate();
        }
        catch(SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM ticket WHERE id = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }
}
