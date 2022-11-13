package com.technology.servlet;

import com.technology.model.User;
import com.technology.service.FriendRequestsService;
import com.technology.service.UserService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(urlPatterns = "/incomingRequests")
@Slf4j
public class IncomingRequestsServlet extends HttpServlet {
  private UserService userService;
  private FriendRequestsService friendRequestsService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    userService = (UserService) config.getServletContext().getAttribute("userService");
    friendRequestsService = (FriendRequestsService) config.getServletContext().getAttribute("friendRequestsService");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    final Long recipientId = (Long) request.getSession().getAttribute("signedInUserId");

    try {
      List<User> incomingRequestsList = userService.getAllIncomingRequestList(recipientId);
      log.info(
          "Displays a number of incoming requests in friends. Number of incoming requests[{}]",
          incomingRequestsList.size()
      );
      request.getServletContext().setAttribute("incomingRequestsList", incomingRequestsList);
      request.getRequestDispatcher("/incomingRequests.jsp").forward(request, response);
    } catch (Exception e) {
      log.error("Error message.", e);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    final Long recipientId = (Long) request.getSession().getAttribute("signedInUserId");
    final Long senderId = Long.parseLong(request.getParameter("requestFriendId"));

    try {
      friendRequestsService.deleteFriendRequest(senderId, recipientId);
      log.info("Delete friend request. SenderId[{}]", senderId);
      response.sendRedirect("./incomingRequests");
    } catch (Exception e) {
      log.error("Error message.", e);
    }
  }
}
