package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection conn = Util.getConnectionJDBS()) {
            String sqlCommand = "CREATE table IF NOT EXISTS Users(\n" +
                    "    id BIGINT primary key GENERATED ALWAYS AS IDENTITY,\n" +
                    "    name varchar,\n" +
                    "    lastName varchar,\n" +
                    "    age int\n" +
                    ")";

            try (PreparedStatement statement = conn.prepareStatement(sqlCommand)) {
                statement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection conn = Util.getConnectionJDBS()) {
            String sqlCommand = "DROP TABLE IF EXISTS Users";

            try (PreparedStatement statement = conn.prepareStatement(sqlCommand)) {
                statement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection conn = Util.getConnectionJDBS()) {
            String sqlCommand = "INSERT INTO Users(name, lastName, age) VALUES (?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(sqlCommand)) {
                stmt.setString(1, name);
                stmt.setString(2, lastName);
                stmt.setByte(3, age);
                stmt.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection conn = Util.getConnectionJDBS()) {
            String sqlCommand = "DELETE FROM Users WHERE id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(sqlCommand)) {
                stmt.setLong(1, id);
                stmt.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection conn = Util.getConnectionJDBS()) {
            String sqlCommand = "SELECT * FROM Users";

            try (PreparedStatement stmt = conn.prepareStatement(sqlCommand)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    String name = rs.getString("name");
                    String lastName = rs.getString("lastName");
                    byte age = rs.getByte("age");

                    User user = new User(name, lastName, age);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    public void cleanUsersTable() {
        try (Connection conn = Util.getConnectionJDBS()) {
            String sqlCommand = "TRUNCATE TABLE Users";

            try (PreparedStatement stmt = conn.prepareStatement(sqlCommand)) {
                stmt.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
