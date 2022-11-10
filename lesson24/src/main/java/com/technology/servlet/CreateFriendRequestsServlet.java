package com.technology.servlet;

import com.technology.service.FriendRequestsService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@WebServlet(urlPatterns = "/createFriendRequests")
@Slf4j
public class CreateFriendRequestsServlet extends HttpServlet {
  private FriendRequestsService friendRequestsService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    friendRequestsService = (FriendRequestsService) config.getServletContext().getAttribute("friendRequestsService");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    final Long senderId = (Long) request.getSession().getAttribute("signedInUserId");
    final Long recipientId = Long.parseLong(request.getParameter("requestFriendId"));

    try {
      if (!friendRequestsService.isRequestExists(senderId, recipientId)) {
        friendRequestsService.createFriendRequest(senderId, recipientId);
        log.info("Request not exists. Create new request senderId=[{}] recipientId=[{}]", senderId, recipientId);
      } else {
        log.info("Request is exists. recipientId=[{}]", recipientId);
      }
      response.sendRedirect("./allUsers");
    } catch (Exception e) {
      log.error("Error message", e);
    }
  }
}
