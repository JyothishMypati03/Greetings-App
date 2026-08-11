package com.bridgelabz.greetingapp.dao;

import com.bridgelabz.greetingapp.model.Greeting;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GreetingDAO {

    Greeting create(Greeting greeting)
            throws SQLException;

    Optional<Greeting> findById(Long id)
            throws SQLException;

    List<Greeting> findAll()
            throws SQLException;

    boolean update(Long id, Greeting greeting)
            throws SQLException;

    boolean delete(Long id)
            throws SQLException;

    List<Greeting> searchByName(String name)
            throws SQLException;

    List<Greeting> findByUserName(String userName)
            throws SQLException;

}
