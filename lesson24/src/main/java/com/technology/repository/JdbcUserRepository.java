package com.technology.repository;

import com.technology.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserRepository implements UserRepository{
    private static final String INSERT_INTO_USERS = "INSERT INTO users(name, password) VALUES (?, ?)";
    private static final String SELECT_FROM_USERS = "SELECT * FROM users";
    private static final String SELECT_FROM_USERS_WHERE_NAME_PASSWORD =
            "SELECT * FROM users WHERE name=? AND password=?";
    private final Connection connection;

    public JdbcUserRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addUser(String name, String password) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USERS)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean validate(String name, String password) {
        boolean status;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USERS_WHERE_NAME_PASSWORD)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            status = resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    @Override
    public List<User> findUsers() {
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_USERS);
            final List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                final User user = new User(
                        resultSet.getString("name"),
                        resultSet.getString("password"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> filterUsersByName(String name, List<User> users) {
        return users.stream().filter(user -> user.getName().equals(name)).toList();
    }
}
