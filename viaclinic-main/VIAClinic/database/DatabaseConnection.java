package com.clinic.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

  private static final String URL =
      "jdbc:postgresql://localhost:5432/postgres";

  private static final String USER = "postgres";
  private static final String PASSWORD = "Kolodziejczyk";

  public static Connection connect() {

    try {
      Connection connection =
          DriverManager.getConnection(URL, USER, PASSWORD);

      System.out.println("Connected");

      return connection;

    } catch (SQLException e) {

      System.out.println("No connection");
      e.printStackTrace();

      return null;
    }
  }
}