package com.bridgelabz.greetingapp;

import com.bridgelabz.greetingapp.dao.GreetingDAO;
import com.bridgelabz.greetingapp.dao.GreetingDAOImpl;
import com.bridgelabz.greetingapp.model.Greeting;
import com.bridgelabz.greetingapp.service.GreetingService;
import com.bridgelabz.greetingapp.util.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {


        // Create DAO object
        GreetingDAO dao = new GreetingDAOImpl();

        // Give DAO to Service
        GreetingService service =
                new GreetingService(dao);

        try {


            // Call Service
            Greeting greeting =
                    service.createGreeting(
                            "Jyothish",
                            "Hello, welcome to My Greetings App!"
                    );

            System.out.println(greeting);

        } catch (SQLException e) {

            e.printStackTrace();

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }
}
