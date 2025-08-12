package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
//        UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();
//        userDao.createUsersTable();
//        userDao.dropUsersTable();
//        userDao.saveUser("Roman", "Laypanov", (byte) 25);
//        userDao.removeUserById(1);
//        System.out.println(userDao.getAllUsers());
//        userDao.cleanUsersTable();

        UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();
//        userDao.createUsersTable();
        userDao.dropUsersTable();
//        userDao.saveUser("Roma", "l", (byte) 24);
//        userDao.removeUserById(1);
//        System.out.println(userDao.getAllUsers());
//        userDao.cleanUsersTable();

    }
}
