package com.technology.repository;

import com.technology.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcUserRepository implements UserRepository{
    private static final String INSERT_INTO_USERS = "INSERT INTO users(name, password) VALUES (?, ?)";
    private static final String SELECT_FROM_USERS = "SELECT * FROM users";
    private static final String SELECT_FROM_USERS_WHERE_NAME_PASSWORD =
            "SELECT * FROM users WHERE name=? AND password=?";
    private static final String SELECT_FROM_USERS_WHERE_NAME = "SELECT * FROM users WHERE name=?";
    private final Connection connection;

    public JdbcUserRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addUser(String name, String password) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USERS)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean validate(String name, String password) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USERS_WHERE_NAME_PASSWORD)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findUsers() {
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_USERS);
            final List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> getUser(String name) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_FROM_USERS_WHERE_NAME)){
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(buildUser(resultSet));
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> filterUsersByQueryParameter(String parameter) {
        final List<User> users = findUsers();
        return users.stream().filter(user -> user.getName().startsWith(parameter)).toList();
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("password"),
                resultSet.getTimestamp("date")
        );
    }
}
