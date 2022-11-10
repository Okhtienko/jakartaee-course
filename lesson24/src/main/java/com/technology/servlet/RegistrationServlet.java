package com.technology.servlet;

import com.technology.service.UserService;

import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

@WebServlet(urlPatterns = "/registration")
@Slf4j
public class RegistrationServlet extends HttpServlet {
  private UserService userService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    userService = (UserService) config.getServletContext().getAttribute("userService");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    final String name = request.getParameter("name");
    final String password = request.getParameter("password");

    try {
      if (!(name.isEmpty() && password.isEmpty())) {
        userService.addUser(name, password);
        log.info("User does not exist, registering a new user. User[{}]", name);
        getServletContext().getRequestDispatcher("/userRegistered.jsp").forward(request, response);
      } else {
        log.info("User is already to exist. User[{}]", name);
        getServletContext().getRequestDispatcher("/accessDenied.jsp").forward(request, response);
      }
    } catch (Exception e) {
      log.error("Error message.", e);
    }
  }
}
