package com.ahmad.saucedemo.data;

import com.ahmad.saucedemo.config.AppConfig;
import com.ahmad.saucedemo.model.CheckoutUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckoutUserRepository {

    private static final String SELECT_BY_ID_SQL =
            "SELECT first_name, last_name, postal_code FROM checkout_user WHERE id = ?";

    public CheckoutUser getUserById(int id) {
        try (Connection conn = DriverManager.getConnection(
                     AppConfig.DB_URL, AppConfig.DB_USER, AppConfig.DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_SQL)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new CheckoutUser(
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("postal_code")
                    );
                } else {
                    throw new IllegalStateException("No checkout_user row with id=" + id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load checkout user from database", e);
        }
    }
}
