package com.technology.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.technology.service.UserService;
import lombok.extern.slf4j.Slf4j;

@WebServlet("/registration")
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
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    final String name = request.getParameter("name");
    final String password = request.getParameter("password");

    try {
      userService.addUser(name, password);
      log.info("User does not exist, registering a new user. User[{}]", name);
      getServletContext().getRequestDispatcher("/userRegistered.jsp").forward(request, response);
    } catch (ServletException e) {
      log.error("Error message", e);
    }
  }
}
