package com.bridgelabz.greetingapp.dao;

import com.bridgelabz.greetingapp.model.Greeting;
import com.bridgelabz.greetingapp.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GreetingDAOImpl implements GreetingDAO{

    @Override
    public Greeting create(Greeting greeting) throws SQLException {

        String sql = "INSERT INTO greetings (user_name, greeting_message) VALUES (?, ?) RETURNING greeting_id, user_name, greeting_message, created_date ";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1,greeting.getUserName());
            preparedStatement.setString(2,greeting.getGreetingMessage());

            try(ResultSet rs = preparedStatement.executeQuery()){

                if(rs.next()){
                    greeting.setGreetingId(rs.getLong("greeting_id"));
                    greeting.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());

                    return greeting;
                }

            }
        }

        return null;
    }

    @Override
    public Optional<Greeting> findById(Long id) throws SQLException {
        String sql = """
            SELECT greeting_id,
                   user_name,
                   greeting_message,
                   created_date
            FROM greetings
            WHERE greeting_id = ?
            """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            // Set ID value
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                // Check whether a row was found
                if (rs.next()) {

                    Greeting greeting = new Greeting();

                    greeting.setGreetingId(
                            rs.getLong("greeting_id")
                    );

                    greeting.setUserName(
                            rs.getString("user_name")
                    );

                    greeting.setGreetingMessage(
                            rs.getString("greeting_message")
                    );

                    greeting.setCreatedDate(
                            rs.getTimestamp("created_date")
                                    .toLocalDateTime()
                    );

                    return Optional.of(greeting);
                }
            }
        }

        // No greeting found
        return Optional.empty();
    }

    @Override
    public List<Greeting> findAll() throws SQLException {
        String sql = """
            SELECT greeting_id,
                   user_name,
                   greeting_message,
                   created_date
            FROM greetings
            ORDER BY greeting_id
            """;

        List<Greeting> greetings = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Greeting greeting = new Greeting();

                greeting.setGreetingId(
                        rs.getLong("greeting_id")
                );

                greeting.setUserName(
                        rs.getString("user_name")
                );

                greeting.setGreetingMessage(
                        rs.getString("greeting_message")
                );

                greeting.setCreatedDate(
                        rs.getTimestamp("created_date")
                                .toLocalDateTime()
                );

                greetings.add(greeting);
            }
        }

        return greetings;
    }

    @Override
    public boolean update(Long id, Greeting greeting) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        return false;
    }

    @Override
    public List<Greeting> searchByName(String name) throws SQLException {
        return null;
    }

    @Override
    public List<Greeting> findByUserName(String userName) throws SQLException {
        return null;
    }

}
