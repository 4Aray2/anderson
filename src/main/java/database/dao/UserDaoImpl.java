package database.dao;

import database.DataBaseException;
import database.DatabaseConnectionManager;

import java.sql.*;
import java.time.LocalDate;

public class UserDaoImpl implements UserDao {

    private static final String INSERT = "INSERT INTO \"user\" (\"name\") VALUES (?)";
    private static final String SELECT = "SELECT * FROM \"user\" WHERE id = ?";
    private static final String DELETE_USER = "DELETE FROM \"user\" WHERE id = ?";
    private static final String DELETE_TICKETS = "DELETE FROM ticket WHERE user_id = ?";

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String CREATION_DATE = "creation_date";

    @Override
    public Long save(User user) {
        Connection connection = null;
        Savepoint savepoint;
        try {
            connection = DatabaseConnectionManager.getConnection();
            connection.setAutoCommit(false);

            savepoint = connection.setSavepoint("USER_INSERT");

            try (PreparedStatement ps = connection.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user.getName());
                ps.executeUpdate();

                try (ResultSet resultSet = ps.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        long id = resultSet.getLong(ID);
                        user.setId(id);
                        connection.commit();
                        return id;
                    } else {
                        throw new DataBaseException("Failed to retrieve generated user id");
                    }
                }
            } catch (SQLException e) {
                if (savepoint != null) {
                    connection.rollback(savepoint);
                }
                throw new DataBaseException("Failed to create user: " + e);
            }
        } catch (SQLException e) {
            throw new DataBaseException("Failed to connect to database: " + e);
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
    }

    @Override
    public User findById(Long id) {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return User.builder()
                            .id(rs.getLong(ID))
                            .name(rs.getString(NAME))
                            .createdDate(rs.getObject(CREATION_DATE, LocalDate.class))
                            .build();
                }
            }

            return null;
        } catch (SQLException e) {
            throw new DataBaseException("Failed to find user " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        Connection connection = null;
        Savepoint savepoint;

        try {
            connection = DatabaseConnectionManager.getConnection();
            connection.setAutoCommit(false);

            savepoint = connection.setSavepoint("USER_DELETE");

            try {
                deleteUserTickets(id, connection);

                try (PreparedStatement ps = connection.prepareStatement(DELETE_USER)) {
                    ps.setLong(1, id);
                    ps.executeUpdate();
                }

                connection.commit();
            } catch (SQLException e) {
                if (savepoint != null) {
                    connection.rollback(savepoint);
                }
                throw new DataBaseException("Failed to delete user: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new DataBaseException("Failed to connect to database: " + e.getMessage());
        } finally {
            DatabaseConnectionManager.closeConnection(connection);
        }
    }

    private void deleteUserTickets(Long userId, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_TICKETS)) {
            ps.setLong(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException("Failed to delete user's tickets: " + e.getMessage());
        }
    }

}
