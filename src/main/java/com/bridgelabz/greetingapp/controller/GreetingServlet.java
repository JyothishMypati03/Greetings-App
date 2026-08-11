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
import java.util.List;
import java.util.Optional;

@WebServlet("/greetings/*")
public class GreetingServlet extends HttpServlet {

    private GreetingService greetingService;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {

        GreetingDAOImpl greetingDAO =
                new GreetingDAOImpl();

        greetingService =
                new GreetingService(greetingDAO);

        objectMapper = new ObjectMapper();

        objectMapper.registerModule(
                new JavaTimeModule()
        );
    }

    // UC-01: Create Greeting
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            Greeting greeting =
                    objectMapper.readValue(
                            request.getInputStream(),
                            Greeting.class
                    );

            Greeting createdGreeting =
                    greetingService.createGreeting(
                            greeting.getUserName(),
                            greeting.getGreetingMessage()
                    );

            response.setContentType(
                    "application/json"
            );

            response.setStatus(
                    HttpServletResponse.SC_CREATED
            );

            objectMapper.writeValue(
                    response.getWriter(),
                    createdGreeting
            );

        } catch (IllegalArgumentException e) {

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

            response.setStatus(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            );

            response.getWriter().write(
                    "{\"error\":\"Database error\"}"
            );
        }
    }

    // UC-02: Get Greeting By ID
    // UC-03: Get All Greetings
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String path =
                    request.getPathInfo();

            response.setContentType(
                    "application/json"
            );

            /*
             * UC-03
             * GET /greetings
             */
            if (path == null || path.equals("/")) {

                List<Greeting> greetings =
                        greetingService.getAllGreetings();

                response.setStatus(
                        HttpServletResponse.SC_OK
                );

                objectMapper.writeValue(
                        response.getWriter(),
                        greetings
                );

                return;
            }

            /*
             * UC-02
             * GET /greetings/{id}
             */

            String idString =
                    path.substring(1);

            Long id =
                    Long.parseLong(idString);

            Optional<Greeting> greeting =
                    greetingService.getGreetingById(id);

            if (greeting.isPresent()) {

                response.setStatus(
                        HttpServletResponse.SC_OK
                );

                objectMapper.writeValue(
                        response.getWriter(),
                        greeting.get()
                );

            } else {

                response.setStatus(
                        HttpServletResponse.SC_NOT_FOUND
                );

                response.getWriter().write(
                        "{\"error\":\"Greeting not found\"}"
                );
            }

        } catch (NumberFormatException e) {

            response.setStatus(
                    HttpServletResponse.SC_BAD_REQUEST
            );

            response.getWriter().write(
                    "{\"error\":\"Invalid greeting ID\"}"
            );

        } catch (IllegalArgumentException e) {

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

            response.setStatus(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            );

            response.getWriter().write(
                    "{\"error\":\"Database error\"}"
            );
        }
    }
}