package com.technology.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(value = "/users.jsp")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final Boolean loggedIn = (Boolean) httpRequest.getSession().getAttribute("user");
        if ((loggedIn == null) || (!loggedIn)) {
            chain.doFilter(request, response);
        } else {
            request.getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
        }
    }
}
