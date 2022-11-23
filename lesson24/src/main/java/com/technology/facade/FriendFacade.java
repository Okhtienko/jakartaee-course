package com.technology.facade;

import com.technology.service.FriendRequestsService;
import com.technology.service.FriendService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FriendFacade {
  private final FriendService friendService;
  private final FriendRequestsService friendRequestsService;

  public FriendFacade(FriendService friendService, FriendRequestsService friendRequestsService) {
    this.friendService = friendService;
    this.friendRequestsService = friendRequestsService;
  }

  public void createFriend(Long senderId, Long recipientId) {
    friendService.addFriend(senderId, recipientId);
    log.info("Create new friend. RecipientId[{}]", recipientId);

    friendRequestsService.deleteFriendRequest(recipientId, senderId);
    log.info("Delete friend request. RecipientId[{}]", recipientId);
  }
}
