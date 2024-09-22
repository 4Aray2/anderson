package database.dao;

public interface UserDao {
    Long save(User user);
    User findById(Long id);
    void deleteById(Long id);
}