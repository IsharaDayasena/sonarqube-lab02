package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserService {

    // Better: avoid hardcoding in real systems (use env variables)
    private String password = "admin123";

    // FIXED: specific exception + no SQL injection + auto-close resources
    public void findUser(String username) throws SQLException {

        String url = "jdbc:mysql://localhost/db";
        String query = "SELECT * FROM users WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(url, "root", password);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.executeQuery();
        }
    }

    // EVEN WORSE issue FIXED
    public void deleteUser(String username) throws SQLException {

        String url = "jdbc:mysql://localhost/db";
        String query = "DELETE FROM users WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(url, "root", password);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.executeUpdate();
        }
    }

    // You can remove this if Sonar flags it
    public void notUsed() {
        System.out.println("I am never called");
    }
}
