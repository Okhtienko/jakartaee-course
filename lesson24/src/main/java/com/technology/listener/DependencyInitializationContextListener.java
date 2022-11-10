package com.technology.listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import lombok.extern.slf4j.Slf4j;

import com.technology.repository.JdbcUserRepository;
import com.technology.repository.UserRepository;
import com.technology.service.UserService;
import com.technology.repository.FriendRequestsRepository;
import com.technology.service.FriendRequestsService;
import com.technology.repository.JdbcFriendRequestsRepository;
import com.technology.repository.FriendRepository;
import com.technology.service.FriendService;
import com.technology.repository.JdbcFriendRepository;

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

      UserRepository userRepository = new JdbcUserRepository(connection);
      UserService userService = new UserService(userRepository);
      sce.getServletContext().setAttribute("userService", userService);

      FriendRequestsRepository friendRequestsRepository = new JdbcFriendRequestsRepository(connection);
      FriendRequestsService friendRequestsService = new FriendRequestsService(friendRequestsRepository);
      sce.getServletContext().setAttribute("friendRequestsService", friendRequestsService);

      FriendRepository friendRepository = new JdbcFriendRepository(connection);
      FriendService friendService = new FriendService(friendRepository);
      sce.getServletContext().setAttribute("friendService", friendService);
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
      e.printStackTrace();
    }
  }
}
