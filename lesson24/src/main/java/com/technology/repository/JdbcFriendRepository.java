package com.technology.repository;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
public class JdbcFriendRepository implements FriendRepository {
  private static final String ADD_FRIEND = "INSERT INTO friends(first_friend_id, second_friend_id) VALUES (?, ?)";
  private static final String DELETE_FRIEND = "DELETE FROM friends " +
      "WHERE (first_friend_id=? AND second_friend_id=?) OR (second_friend_id=? AND first_friend_id=?)";
  private final Connection connection;

  public JdbcFriendRepository(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void addFriend(Long senderId, Long recipientId) {
    try (PreparedStatement statement = connection.prepareStatement(ADD_FRIEND)) {
      statement.setLong(1, senderId);
      statement.setLong(2, recipientId);
      statement.execute();
    } catch (SQLException e) {
      log.error(
          "Record not added to db. SenderId[{}], recipientId[{}]. SQL exception{}",
          senderId, recipientId, e
      );
    }
  }

  @Override
  public void deleteFriend(Long signedInUserId, Long friendId) {
    try (PreparedStatement statement = connection.prepareStatement(DELETE_FRIEND)) {
      statement.setLong(1, signedInUserId);
      statement.setLong(2, friendId);
      statement.setLong(3, signedInUserId);
      statement.setLong(4, friendId);
      statement.executeUpdate();
    } catch (SQLException e) {
      log.error(
          "Record not removed from db. SignedInUserId[{}], friendId[{}].  SQL exception{}",
          signedInUserId,  friendId, e
      );
    }
  }
}
