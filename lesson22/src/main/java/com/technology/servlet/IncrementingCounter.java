package com.technology.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(name = "incrementingCounter", urlPatterns = "/counter")
public class IncrementingCounter extends HttpServlet {
    private AtomicInteger counter;

    @Override
    public void init() throws ServletException {
        super.init();
        counter = new AtomicInteger();
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        try(PrintWriter printWriter = response.getWriter()) {
            printWriter.println("<h3>Number of requests: " + counter + "</h3>");
            printWriter.println("<h3>Header names: " + request.getHeaderNames() + "</h3>");
            printWriter.println("<h3>Servlet path: " + request.getServletPath() + "</h3>");
            printWriter.println("<h3>Remote ID-address: " + request.getLocalAddr() +"</h3>");
            printWriter.println("<h3>Method: " +  request.getMethod() + "</h3>");
        }
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws  IOException {
        request.setCharacterEncoding("UTF-8");
        counter.getAndIncrement();
    }
}
