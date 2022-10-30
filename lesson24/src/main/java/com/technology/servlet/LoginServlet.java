package com.technology.servlet;

import java.io.IOException;
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

@WebServlet("/login")
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
      final List<User> users = userService.filterUsersByName(name);
      request.setAttribute("users", users);
      log.info(
          "Displays a list filtered by name. List users{}",
          users.stream().map(User::getName).toList()
      );
      getServletContext().getRequestDispatcher("/users.jsp").forward(request, response);
    } catch (Exception e) {
      log.error("Error message", e);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    final String name = request.getParameter("name");
    final String password = request.getParameter("password");

    try {
      if (userService.validate(name, password)) {
        request.getSession().setAttribute("isLoggedIn", true);
        final List<User> users = userService.findUsers();
        request.setAttribute("users", users);
        log.info(
            "Displays a list of registered users. List users{}",
            users.stream().map(User::getName).toList()
        );
        getServletContext().getRequestDispatcher("/users.jsp").forward(request, response);
      }
    } catch (ServletException e) {
      log.error("Error message", e);
    }
  }
}
