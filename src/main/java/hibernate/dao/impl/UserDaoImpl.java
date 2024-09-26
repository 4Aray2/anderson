package hibernate.dao.impl;

import hibernate.dao.UserDao;
import hibernate.model.User;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@AllArgsConstructor
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    @Override
    public void save(User user) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.persist(user);
                session.getTransaction().commit();
            } catch (Exception e) {
                if (session.getTransaction() != null && session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                throw new RuntimeException("Error while saving user", e);
            }
        }
    }


    @Override
    public User findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                User user = session.get(User.class, id);
                session.getTransaction().commit();
                return user;
            } catch (Exception e) {
                if (session.getTransaction() != null && session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                throw new RuntimeException("Error while finding user by id", e);
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();

                User user = session.get(User.class, id);
                if (user != null) {
                    session.remove(user);
                }

                session.getTransaction().commit();
            } catch (Exception e) {
                if (session.getTransaction() != null && session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                throw new RuntimeException("Error while deleting user", e);
            }
        }
    }
}
