package com.technology.servlet;

import com.technology.model.User;
import com.technology.service.UserService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/suggestedFriends")
@Slf4j
public class SuggestedFriends extends HttpServlet {
  private UserService userService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    userService = (UserService) config.getServletContext().getAttribute("userService");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    final Long signedInUserId = (Long) request.getSession().getAttribute("signedInUserId");

    try {
      final List<User> suggestedFriends = userService.getSuggestedFriendsList(signedInUserId);
      log.info("Displays a number of suggested friends. Number of suggested friends[{}]", suggestedFriends.size());
      request.setAttribute("suggestedFriends", suggestedFriends);
      request.getServletContext().getRequestDispatcher("/suggestedFriends.jsp").forward(request, response);
    } catch (Exception e) {
      log.error("Error message.", e);
    }
  }
}
