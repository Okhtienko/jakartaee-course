package com.technology.servlet;

import com.technology.model.User;
import com.technology.service.FriendRequestsService;
import com.technology.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@WebServlet(urlPatterns = "/outgoingRequests")
@Slf4j
public class OutgoingFriendRequestsServlet extends HttpServlet {
  private UserService userService;
  private FriendRequestsService friendRequestsService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    userService = (UserService) config.getServletContext().getAttribute("userService");
    friendRequestsService = (FriendRequestsService) config.getServletContext().getAttribute("friendRequestsService");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    final Long senderId = (Long) request.getSession().getAttribute("signedInUserId");

    try {
      List<User> outgoingRequestsList = userService.getAllOutgoingRequestList(senderId);
      log.info("Displays a number of requests in friends. Number of requests[{}]", outgoingRequestsList.size());
      request.getServletContext().setAttribute("outgoingRequestsList", outgoingRequestsList);
      request.getRequestDispatcher("/outgoingRequests.jsp").forward(request, response);
    } catch (Exception e) {
      log.error("Error message.", e);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    final Long senderId = (Long) request.getSession().getAttribute("signedInUserId");
    final Long recipientId = Long.parseLong(request.getParameter("requestFriendId"));

    try {
      friendRequestsService.deleteFriendRequest(senderId, recipientId);
      log.info("Delete friend request. RecipientId[{}]", recipientId);
      response.sendRedirect("./outgoingRequests");
    } catch (Exception e) {
      log.error("Error message.", e);
    }
  }
}
