package com.technology.servlet;

import com.technology.service.FriendRequestsService;
import com.technology.service.FriendService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/acceptRequests")
@Slf4j
public class AddFriendServlet extends HttpServlet {
  private FriendService friendService;
  private FriendRequestsService friendRequestsService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    friendService = (FriendService) config.getServletContext().getAttribute("friendService");
    friendRequestsService = (FriendRequestsService) config.getServletContext().getAttribute("friendRequestsService");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    final Long senderId = (Long) request.getSession().getAttribute("signedInUserId");
    final Long recipientId = Long.parseLong(request.getParameter("requestFriendId"));

    try {
      friendService.addFriend(senderId, recipientId);
      log.info("Create new friend. recipientId=[{}]", recipientId);
      friendRequestsService.deleteFriendRequest(recipientId, senderId);
      log.info("Delete friend request. recipientId=[{}]", recipientId);
      response.sendRedirect("./incomingRequests");
    } catch (Exception e) {
      log.error("Error message", e);
    }
  }
}
