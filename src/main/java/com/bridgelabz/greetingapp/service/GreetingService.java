package com.bridgelabz.greetingapp.service;

import com.bridgelabz.greetingapp.dao.GreetingDAO;
import com.bridgelabz.greetingapp.model.Greeting;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class GreetingService {

    private final GreetingDAO greetingDAO;

    public GreetingService(GreetingDAO greetingDAO) {
        this.greetingDAO = greetingDAO;
    }

    // UC-01
    public Greeting createGreeting(String userName,
                                   String message)
            throws SQLException {

        if (userName == null || userName.isBlank()) {
            throw new IllegalArgumentException(
                    "User name cannot be empty"
            );
        }

        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException(
                    "Greeting message cannot be empty"
            );
        }

        Greeting greeting = new Greeting();

        greeting.setUserName(userName);
        greeting.setGreetingMessage(message);

        return greetingDAO.create(greeting);
    }

    public Optional<Greeting> getGreetingById(Long id)
            throws SQLException {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "Greeting ID must be greater than 0"
            );
        }

        return greetingDAO.findById(id);
    }

    public List<Greeting> getAllGreetings()
            throws SQLException {

        return greetingDAO.findAll();
    }

}
