package hibernate.dao;

import hibernate.model.User;

public interface UserDao {
    void save(User user);
    User findById(Long id);
    void deleteById(Long id);
}
