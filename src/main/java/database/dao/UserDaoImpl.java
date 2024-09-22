package database.dao;

import database.DataBaseException;
import database.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String CREATION_DATE = "creation_date";

    @Override
    public void save(User user) {
        String sql = "INSERT INTO \"user\" (\"name\") VALUES (?)";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getName());
            int rowsAffected = ps.executeUpdate();

            if(rowsAffected > 0) {
                ResultSet result = ps.getGeneratedKeys();
                if(result.next()) {
                    long id = result.getLong(ID);
                    user.setId(id);
                }
            } else {
                throw new DataBaseException("did not saved properly!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(Long id) {
        String sql = "SELECT * FROM \"user\" WHERE id = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return  User.builder()
                        .id(rs.getLong(ID))
                        .name(rs.getString(NAME))
                        .createdDate(rs.getObject(CREATION_DATE, LocalDate.class))
                        .build();
            }
            return null;
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        deleteUserTickets(id);
        String sql = "DELETE FROM \"user\" WHERE id = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    private void deleteUserTickets(Long id) {
        User userToDelete = findById(id);
        TicketDao ticketDao = DaoFactory.createTicketDao();
        List<Ticket> ticketsToDelete = ticketDao.findByUserId(userToDelete);
        for (Ticket ticketToDelete : ticketsToDelete) {
            ticketDao.deleteById(ticketToDelete.getId());
        }
    }
}
