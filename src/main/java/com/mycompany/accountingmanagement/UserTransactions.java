/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.accountingmanagement;

import java.sql.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author nagihan_imamoğlu
 */
public class UserTransactions {
    
private static final String connectionString = "jdbc:mysql://localhost:3306/accountingmanagement?user=root&password=";
    
    public static boolean addTransaction(String type, String category, float amount, String description, int user_id, String username_surname, String currency) {

        String query = "INSERT INTO transactions (type, category, amount, description, user_id, username_surname, currency) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection connection = DriverManager.getConnection(connectionString);
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, type);
            statement.setString(2, category);
            statement.setFloat(3, amount);
            statement.setString(4, description);
            statement.setInt(5, user_id);
            statement.setString(6, username_surname);
            statement.setString(7, currency);

            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Insertion Error: " + ex.getMessage());
            return false;
        }
    }
    
    public static boolean deleteTransaction(int id, int user_id) {

        String query = "DELETE FROM transactions WHERE id = ? AND user_id = ?";

        try (
                Connection connection = DriverManager.getConnection(connectionString); 
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setInt(2, user_id);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.out.println("Deletion Error: " + ex.getMessage());
            return false;
        }
    }
    
    public static boolean updateTransaction(int id , int user_id, String type, String category, float amount, String description, String currency) {
        
        String query = "UPDATE transactions SET type = ?, category = ?, amount = ?, description = ?, currency = ? WHERE id = ? AND user_id = ?";

        try (
                Connection connection = DriverManager.getConnection(connectionString);
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, type);
            statement.setString(2, category);
            statement.setFloat(3, amount);
            statement.setString(4, description);
            statement.setString(5, currency); 
            statement.setInt(6, id);
            statement.setInt(7, user_id);
            
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.out.println("Update Error: " + ex.getMessage());
            return false;
        }
    }     
    
    public static DefaultTableModel accessTransactions(int user_id){
        DefaultTableModel model = new DefaultTableModel(new String[]{
                "id","type","category","amount","description","currency"},0);
        
        String query = "SELECT * FROM transactions WHERE user_id = ?";
        
        try (
                Connection connection = DriverManager.getConnection(connectionString); 
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, user_id);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("type"),
                    rs.getString("category"),
                    rs.getFloat("amount"),
                    rs.getString("description"),
                    rs.getString("currency")
                });
            }
        } catch (SQLException ex) {
            System.out.println("Accessing Error: " + ex.getMessage());
        }
        return model;
    }
    
    public static float[] accessFinancialSummary(int user_id){
        
        float total_income = 0;
        float total_expense = 0;
        
        String query = "SELECT amount, type FROM transactions  WHERE USER_id = ?";

        try (
                Connection connection = DriverManager.getConnection(connectionString);
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, user_id);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
               float amount = rs.getFloat("amount");
               String type = rs.getString("type");
               
               if("income".equalsIgnoreCase(type)){
                   total_income += amount;
               }else if("expense".equalsIgnoreCase(type)){
                   total_expense += amount;
               }
            }
        } catch (SQLException ex) {
            System.out.println("Accessing Error: " + ex.getMessage());
        }
        return new float[]{total_income, total_expense, total_income - total_expense};
    }
}
