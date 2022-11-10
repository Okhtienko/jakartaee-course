package com.technology.service;

import com.technology.repository.FriendRepository;

public class FriendService {
  private final FriendRepository friendRepository;

  public FriendService(FriendRepository friendRepository) {
    this.friendRepository = friendRepository;
  }

  public void addFriend(Long senderId, Long recipientId) {
    friendRepository.addFriend(senderId, recipientId);
  }

  public void deleteFriend(Long friendId) {
    friendRepository.deleteFriend(friendId);
  }
}
