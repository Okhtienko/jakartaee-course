package com.technology.service;

import com.technology.repository.FriendRequestsRepository;

public class FriendRequestsService {
  private final FriendRequestsRepository friendRequestsRepository;

  public FriendRequestsService(FriendRequestsRepository friendRequestsRepository) {
    this.friendRequestsRepository = friendRequestsRepository;
  }

  public void createFriendRequest(Long senderId, Long recipientId) {
    friendRequestsRepository.createFriendRequest(senderId, recipientId);
  }

  public void deleteFriendRequest(Long senderId, Long recipientId) {
    friendRequestsRepository.deleteFriendRequest(senderId, recipientId);
  }

  public boolean isRequestExists(Long senderId, Long recipientId) {
    return friendRequestsRepository.isRequestExists(senderId, recipientId);
  }
}
