package com.technology.servlet;

import com.technology.facade.FriendFacade;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/acceptingRequests")
@Slf4j
public class AddFriendServlet extends HttpServlet {

  private FriendFacade friendFacade;

  @Override
  public void init(ServletConfig config) throws ServletException {
    friendFacade = (FriendFacade) config.getServletContext().getAttribute("friendFacade");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    final Long senderId = (Long) request.getSession().getAttribute("signedInUserId");
    final Long recipientId = Long.parseLong(request.getParameter("requestFriendId"));

    try {
      friendFacade.createFriend(senderId, recipientId);
      response.sendRedirect("./incomingRequests");
    } catch (Exception e) {
      log.error("Error message.", e);
    }
  }
}
