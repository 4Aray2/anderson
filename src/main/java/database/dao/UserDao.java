package database.dao;

public interface UserDao {
    void save(User user);
    User findById(Long id);
    void deleteById(Long id);
}