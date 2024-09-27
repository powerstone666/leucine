package com.backend.leucinebackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.leucinebackend.entity.User;
import com.backend.leucinebackend.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RestController
public class HealthCheckController {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserRepository studentRepository;
// Replace with your actual repository

    @GetMapping("/db-check")
    public ResponseEntity<String> dbCheck() {
        // Fetch data from your entity
        List<User> data = studentRepository.findAll(); // Replace with your actual entity
        return ResponseEntity.ok("Database is connected. Data count: " + data.size());
    }

    @GetMapping("/health")
    public String healthCheck() {
        String dbStatus;
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(2)) {
                dbStatus = "Database is up and running";
            } else {
                dbStatus = "Database connection is invalid";
            }
        } catch (SQLException e) {
            dbStatus = "Database connection failed: " + e.getMessage();
        }

        return "Application is running. " + dbStatus;
    }
}
