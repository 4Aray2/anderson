package hibernate.service;

import hibernate.dao.DaoFactory;
import hibernate.dao.UserDao;
import hibernate.model.User;

public class UserService {

    private final UserDao userDao;

    public UserService() {
        this.userDao = DaoFactory.createFactory().getUserDao();
    }

    public void save(User user) {
        userDao.save(user);
    }

    public User findById(Long id) {
        return userDao.findById(id);
    }

    public void deleteById(Long id) {
        userDao.deleteById(id);
    }
}
