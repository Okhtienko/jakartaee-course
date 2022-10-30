package com.technology.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.technology.model.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JdbcUserRepository implements UserRepository {
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
    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USERS)) {
      preparedStatement.setString(1, name);
      preparedStatement.setString(2, password);
      preparedStatement.execute();
    } catch (SQLException e) {
      log.error("Error message.", e);
    }
  }

  @Override
  public boolean validate(String name, String password) {
    try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USERS_WHERE_NAME_PASSWORD)) {
      preparedStatement.setString(1, name);
      preparedStatement.setString(2, password);
      ResultSet resultSet = preparedStatement.executeQuery();
      return resultSet.next();
    } catch (SQLException e) {
      log.error("Error message.", e);
    }
    return false;
  }

  @Override
  public List<User> findUsers() {
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery(SELECT_FROM_USERS);
      final List<User> users = new ArrayList<>();
      while (resultSet.next()) {
        users.add(buildUser(resultSet));
      }
      return users;
    } catch (SQLException e) {
      log.error("Error message.", e);
    }
    return new ArrayList<>();
  }

  @Override
  public Optional<User> getUser(String name) {
    try (PreparedStatement statement = connection.prepareStatement(SELECT_FROM_USERS_WHERE_NAME)) {
      statement.setString(1, name);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        return Optional.of(buildUser(resultSet));
      }

    } catch (SQLException e) {
      log.error("Error message", e);
    }
    return Optional.empty();
  }

  @Override
  public List<User> filterUsersByName(String parameter) {
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
