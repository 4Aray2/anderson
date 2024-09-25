package database.dao.impl;

import database.exception.DataBaseException;
import database.DatabaseConnectionManager;
import database.model.Ticket;
import database.dao.TicketDao;
import database.model.User;
import ticket.model.TicketType;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TicketDaoImpl implements TicketDao {

    private static final String INSERT = "INSERT INTO ticket(user_id, ticket_type) VALUES (?, ?::ticket_type)";
    private static final String SELECT_BY_ID = "SELECT t.*, u.\"name\", u.creation_date AS user_creation_date " +
            "FROM ticket t LEFT JOIN \"user\" u ON t.user_id = u.id WHERE t.id = ?";

    private static final String SELECT_BY_USER = "SELECT t.*, u.\"name\", u.creation_date AS user_creation_date " +
            "FROM ticket t LEFT JOIN \"user\" u ON t.user_id = u.id WHERE user_id = ?";

    private static final String UPDATE = "UPDATE ticket SET ticket_type=?::ticket_type WHERE id=?";

    private static final String ID = "id";
    private static final String USER_ID = "user_id";
    private static final String TICKET_TYPE = "ticket_type";
    private static final String CREATION_DATE = "creation_date";
    private static final String NAME = "name";
    private static final String USER_CREATION_DATE = "user_creation_date";

    @Override
    public Long save(Ticket ticket) {
        Connection connection = null;
        Savepoint savepoint;
        try {
            connection = DatabaseConnectionManager.getConnection();
            connection.setAutoCommit(false);

            savepoint = connection.setSavepoint("TICKET_INSERT");

            try (PreparedStatement ps = connection.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setLong(1, ticket.getUser().getId());
                ps.setString(2, ticket.getTicketType().name());
                ps.executeUpdate();

                try (ResultSet resultSet = ps.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        long id = resultSet.getLong(ID);
                        ticket.setId(id);
                        connection.commit();
                        return id;
                    } else {
                        throw new DataBaseException("Failed to retrieve generated ticket id");
                    }
                }
            } catch (SQLException e) {
                if (savepoint != null) {
                    connection.rollback(savepoint);
                }
                throw new DataBaseException("Failed to create ticket: " + e);
            }
        } catch (SQLException e) {
            throw new DataBaseException("Failed to connect to database: " + e);
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
    }

    @Override
    public Ticket findById(Long id) {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Ticket.builder()
                            .id(rs.getLong(ID))
                            .user(getUser(rs))
                            .ticketType(TicketType.valueOf(rs.getString(TICKET_TYPE)))
                            .creationDate(rs.getObject(CREATION_DATE, LocalDate.class))
                            .build();
                }
            }

            return null;
        } catch (SQLException e) {
            throw new DataBaseException("Failed to find ticket " + e.getMessage());
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
    public List<Ticket> findByUserId(Long userId) {
        List<Ticket> tickets = new ArrayList<>();

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_BY_USER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tickets.add(Ticket.builder()
                            .id(rs.getLong(ID))
                            .user(getUser(rs))
                            .ticketType(TicketType.valueOf(rs.getString(TICKET_TYPE)))
                            .creationDate(rs.getObject(CREATION_DATE, LocalDate.class))
                            .build());
                }
            }

            return tickets;
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    @Override
    public Long updateTicketTypeById(Long ticketId, TicketType ticketType) {
        Connection connection = null;
        Savepoint savepoint;
        try {
            connection = DatabaseConnectionManager.getConnection();
            connection.setAutoCommit(false);

            savepoint = connection.setSavepoint("UPDATE_TICKET");

            try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
                ps.setString(1, ticketType.name());
                ps.setLong(2, ticketId);

                int affectedRows = ps.executeUpdate();

                if (affectedRows > 0) {
                    connection.commit();
                    return ticketId;
                } else {
                    throw new DataBaseException("Failed to update ticket: No rows affected.");
                }
            } catch (SQLException e) {
                if (savepoint != null) {
                    connection.rollback(savepoint);
                }
                throw new DataBaseException("Failed to update ticket: " + e);
            }
        } catch(SQLException e) {
            throw new DataBaseException("Failed to connect to database: " + e);
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
    }
}
