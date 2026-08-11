package com.bridgelabz.greetingapp.service;

import com.bridgelabz.greetingapp.dao.GreetingDAO;
import com.bridgelabz.greetingapp.model.Greeting;

import java.sql.SQLException;

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

}
