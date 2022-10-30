package com.technology.listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.technology.repository.JdbcUserRepository;
import com.technology.repository.UserRepository;
import com.technology.service.UserService;
import lombok.extern.slf4j.Slf4j;

@WebListener
@Slf4j
public class DependencyInitializationContextListener implements ServletContextListener {
  @Override
  public void contextInitialized(final ServletContextEvent sce) {
    final String dbDriver = "org.postgresql.Driver";
    final String username = sce.getServletContext().getInitParameter("db_user");
    final String password = sce.getServletContext().getInitParameter("db_password");
    final String dbUrl = sce.getServletContext().getInitParameter("db_url");

    try {
      Class.forName(dbDriver);
      final Connection connection = DriverManager.getConnection(dbUrl, username, password);
      UserRepository repository = new JdbcUserRepository(connection);
      UserService userService = new UserService(repository);
      sce.getServletContext().setAttribute("userService", userService);
    } catch (Exception e) {
      log.error("Error message", e);
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    try {
      final Connection connection = (Connection) sce.getServletContext().getAttribute("connection");
      connection.close();
    } catch (SQLException e) {
      log.error("Error message", e);
      e.printStackTrace();
    }
  }
}
