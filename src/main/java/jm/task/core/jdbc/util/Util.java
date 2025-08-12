package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    public static Connection getConnectionJDBS() throws SQLException, IOException {
        Properties prop = new Properties();
        try (InputStream in = Util.class.getClassLoader().getResourceAsStream("postgres.properties")) {
            prop.load(in);
        }

        String jdbcUrl = prop.getProperty("url");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        return DriverManager.getConnection(jdbcUrl, username, password);
    }

    public static Session getConnectionHiber() {
        Configuration config = new Configuration().addAnnotatedClass(User.class);
        SessionFactory sessionFactory = config.buildSessionFactory();

        return sessionFactory.getCurrentSession();
    }
}
