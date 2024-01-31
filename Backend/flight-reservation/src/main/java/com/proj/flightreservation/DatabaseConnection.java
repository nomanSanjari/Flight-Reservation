//package com.sample.example.sampleApp;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class DatabaseConnection {
//
//    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/AirlineCompany";
//    private static final String USERNAME = "user480";
//    private static final String PASSWORD = "ensf480";
//
//    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
//    }
//
//    public static List<String> getAllUserNames() {
//        List<String> userNames = new ArrayList<>();
//
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement("SELECT UserName FROM User");
//             ResultSet resultSet = preparedStatement.executeQuery()) {
//
//            while (resultSet.next()) {
//                String userName = resultSet.getString("UserName");
//                userNames.add(userName);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace(); // Handle the exception according to your application's requirements
//        }
//
//        return userNames;
//    }
//
//    public static void main(String[] args) {
//        // Example usage: Print all user names from the "User" table
//        List<String> userNames = getAllUserNames();
//        for (String userName : userNames) {
//            System.out.println(userName);
//        }
//    }
//}
