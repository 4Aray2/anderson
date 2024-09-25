package database.dao;

import database.model.User;

public interface UserDao {
    Long save(User user);
    User findById(Long id);
    void deleteById(Long id);
}