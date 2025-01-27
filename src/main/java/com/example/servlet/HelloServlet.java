package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
