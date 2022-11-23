package com.technology.servlet;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.technology.model.User;
import com.technology.service.UserService;
import lombok.extern.slf4j.Slf4j;

@WebServlet(urlPatterns = "/login")
@Slf4j
public class LoginServlet extends HttpServlet {
  private UserService userService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    userService = (UserService) config.getServletContext().getAttribute("userService");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    final String name = request.getParameter("name");

    try {
      final List<User> suggestedFriends = userService.filterUsersByName(name);
      log.info(
          "Displays a number of suggested friends filtered by name. Number of suggested friends[{}]",
          suggestedFriends.size()
      );
      request.setAttribute("suggestedFriends", suggestedFriends);
      getServletContext().getRequestDispatcher("/suggestedFriends.jsp").forward(request, response);
    } catch (Exception e) {
      log.error("Error message.", e);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    final String name = request.getParameter("name");
    final String password = request.getParameter("password");
    final Long signedInUserId = userService.getUserId(name);

    try {
      if (userService.validate(name, password)) {
        request.getSession().setAttribute("signedInUserId", signedInUserId);
        request.getSession().setAttribute("signedUserName", name);

        final List<User> suggestedFriends = userService.getSuggestedFriendsList(signedInUserId);
        log.info("Displays a number of suggested friends. Number of suggested friends[{}]", suggestedFriends.size());
        request.setAttribute("suggestedFriends", suggestedFriends);
        request.getServletContext().getRequestDispatcher("/suggestedFriends.jsp").forward(request, response);
      }
    } catch (Exception e) {
      log.error("Error message.", e);
    }
  }
}
