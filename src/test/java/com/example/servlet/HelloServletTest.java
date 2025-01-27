package com.example.servlet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

class HelloServletTest {
    
    private HelloServlet servlet;
    
    @Mock
    private HttpServletRequest request;
    
    @Mock
    private HttpServletResponse response;
    
    @Mock
    private RequestDispatcher dispatcher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        servlet = new HelloServlet();
    }

    @Test
    void testDoGetWithName() throws ServletException, IOException {
        // Given
        String name = "John";
        when(request.getParameter("name")).thenReturn(name);
        when(request.getRequestDispatcher("/WEB-INF/views/hello.jsp")).thenReturn(dispatcher);

        // When
        servlet.doGet(request, response);

        // Then
        verify(request).setAttribute("username", name);
        verify(request).getRequestDispatcher("/WEB-INF/views/hello.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoGetWithoutName() throws ServletException, IOException {
        // Given
        when(request.getParameter("name")).thenReturn(null);
        when(request.getRequestDispatcher("/WEB-INF/views/hello.jsp")).thenReturn(dispatcher);

        // When
        servlet.doGet(request, response);

        // Then
        verify(request).setAttribute("username", "Guest");
        verify(request).getRequestDispatcher("/WEB-INF/views/hello.jsp");
        verify(dispatcher).forward(request, response);
    }
}
