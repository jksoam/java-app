package com.example.servlet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class HelloServletTest {
    
    private HelloServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;

    @Before
    public void setUp() {
        servlet = new HelloServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
    }

    @Test
    public void testDoGetWithName() throws ServletException, IOException {
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
    public void testDoGetWithoutName() throws ServletException, IOException {
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
