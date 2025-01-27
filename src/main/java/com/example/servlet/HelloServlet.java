package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("name");
        if (username == null || username.isEmpty()) {
            username = "Guest";
        }
        
        request.setAttribute("username", username);
        request.setAttribute("currentTime", LocalDateTime.now());
        request.getRequestDispatcher("/WEB-INF/views/hello.jsp").forward(request, response);
    }
}
