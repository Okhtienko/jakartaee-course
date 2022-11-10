package com.technology.repository;

public interface FriendRepository {
  void addFriend(Long senderId, Long recipientId);

  void deleteFriend(Long friendId);
}
