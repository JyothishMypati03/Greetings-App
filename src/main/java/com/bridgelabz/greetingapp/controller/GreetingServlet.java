package com.bridgelabz.greetingapp.controller;

import com.bridgelabz.greetingapp.dao.GreetingDAOImpl;
import com.bridgelabz.greetingapp.model.Greeting;
import com.bridgelabz.greetingapp.service.GreetingService;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/greetings")
public class GreetingServlet extends HttpServlet {

    private GreetingService greetingService;

    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {

        GreetingDAOImpl greetingDAO =
                new GreetingDAOImpl();

        greetingService =
                new GreetingService(greetingDAO);

        objectMapper =
                new ObjectMapper();
        objectMapper.registerModule(
                new JavaTimeModule());
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            // JSON → Greeting object
            Greeting greeting =
                    objectMapper.readValue(
                            request.getInputStream(),
                            Greeting.class
                    );

            // Call Service
            Greeting createdGreeting =
                    greetingService.createGreeting(
                            greeting.getUserName(),
                            greeting.getGreetingMessage()
                    );

            // Greeting object → JSON
            String jsonResponse =
                    objectMapper.writeValueAsString(
                            createdGreeting
                    );

            response.setContentType(
                    "application/json"
            );

            response.setStatus(
                    HttpServletResponse.SC_CREATED
            );

            response.getWriter().write(
                    jsonResponse
            );

        } catch (IllegalArgumentException e) {

            response.setContentType(
                    "application/json"
            );

            response.setStatus(
                    HttpServletResponse.SC_BAD_REQUEST
            );

            response.getWriter().write(
                    "{\"error\":\""
                            + e.getMessage()
                            + "\"}"
            );

        } catch (SQLException e) {

            e.printStackTrace();

            response.setContentType(
                    "application/json"
            );

            response.setStatus(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            );

            response.getWriter().write(
                    "{\"error\":\"Database error\"}"
            );
        }
    }
}