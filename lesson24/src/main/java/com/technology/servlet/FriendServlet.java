package com.technology.servlet;

import com.technology.model.User;
import com.technology.service.FriendService;
import com.technology.service.UserService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(urlPatterns = "/friends")
@Slf4j
public class FriendServlet extends HttpServlet {
  private UserService userService;
  private FriendService friendService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    userService = (UserService) config.getServletContext().getAttribute("userService");
    friendService = (FriendService) config.getServletContext().getAttribute("friendService");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    final Long signedInUser = (Long) request.getSession().getAttribute("signedInUserId");

    try {
      List<User> friends = userService.getFriendsList(signedInUser);
      log.info("Displays a number of friends. Number of friends[{}]", friends.size());
      request.getServletContext().setAttribute("friends", friends);
      request.getRequestDispatcher("/friends.jsp").forward(request, response);
    } catch (Exception e) {
      log.error("Error message.", e);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    final Long signedInUserId = (Long) request.getSession().getAttribute("signedInUserId");
    final Long friendId = Long.parseLong(request.getParameter("requestFriendId"));

    try {
      friendService.deleteFriend(signedInUserId, friendId);
      log.info("Delete friend. FriendId[{}]", friendId);
      response.sendRedirect("./friends");
    } catch (Exception e) {
      log.error("Error message.", e);
    }
  }
}
