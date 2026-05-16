/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.accountingmanagement;

import java.sql.*;

/**
 *
 * @author nagihan_imamoğlu
 */
public class AdminTransactions {

    private static final String connectionString = "jdbc:mysql://localhost:3306/accountingmanagement?user=root&password=";

    public static boolean addUser(String name_surname, String email, String password, String gender, String role) {

        String query = "INSERT INTO users (name_surname, email, password, gender, role) VALUES(?, ?, ?, ?, ?)";

        try (
                Connection connection = DriverManager.getConnection(connectionString);
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name_surname);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, gender);
            statement.setString(5, role);

            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Insertion Error: " + ex.getMessage());
            return false;
        }
    }

    public static boolean deleteUser(int id) {

        String query = "DELETE FROM users WHERE id = ?";

        try (
                Connection connection = DriverManager.getConnection(connectionString); 
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.out.println("Deletion Error: " + ex.getMessage());
            return false;
        }
    }
    
    public static boolean updateUser(int id, String name_surname, String email, String password, String gender, String role) {
        
        String query = "UPDATE users SET name_surname = ?, email = ?, password = ?, gender = ?, role = ? WHERE id = ?";

        try (
                Connection connection = DriverManager.getConnection(connectionString);
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name_surname);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, gender);
            statement.setString(5, role); 
            statement.setInt(6, id);
            
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.out.println("Update Error: " + ex.getMessage());
            return false;
        }
    }  
}
