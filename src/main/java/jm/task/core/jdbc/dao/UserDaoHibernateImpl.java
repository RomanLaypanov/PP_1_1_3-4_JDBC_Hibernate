package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Statement;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getConnectionHiber()) {
            Transaction tx = session.beginTransaction();

            session.doWork(connection -> {
                try (Statement stmt = connection.createStatement()) {
                    stmt.execute("CREATE TABLE IF NOT EXISTS Users (" +
                            "id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY," +
                            "name VARCHAR(50)," +
                            "lastName VARCHAR(50)," +
                            "age INTEGER)");
                }
            });

            tx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getConnectionHiber()) {
            Transaction tx = session.beginTransaction();

            session.doWork(connection -> {
                try (Statement stmt = connection.createStatement()) {
                    stmt.execute("DROP TABLE IF EXISTS Users");
                }
            });

            tx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getConnectionHiber()) {
            Transaction tx = session.beginTransaction();

            session.persist(new User(name, lastName, age));

            tx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getConnectionHiber()) {
            Transaction tx = session.beginTransaction();

            session.remove(session.find(User.class, id));

            tx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;

        try (Session session = Util.getConnectionHiber()) {
            Transaction tx = session.beginTransaction();

            users = session.createQuery("FROM User", User.class).list();

            tx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getConnectionHiber()) {
            Transaction tx = session.beginTransaction();

            session.createQuery("DELETE FROM User").executeUpdate();

            tx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}