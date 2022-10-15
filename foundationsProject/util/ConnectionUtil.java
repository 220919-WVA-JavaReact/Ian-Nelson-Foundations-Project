package com.revature.strings.foundationsProject.util;


//We're going to make a Singleton connection instance


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionUtil {

    private static Connection conn = null;

    //private constructor

    private ConnectionUtil() {

    }

    public static Connection getConnection() {
        //  Check to see if there is a connection instance already
        // If there is return it

        try {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Use a previously made connection");
                return conn;
            }
        } catch (SQLException e) {
            //Print out the stack trace and just return null
            e.printStackTrace();
            return null;
        }


       /* String url = System.getenv("url");
        String username = System.getenv("username");
        String password = System.getenv("password"); */
        String url = "";
        String username = "";
        String password = "";
        //loading in from application.properties
        Properties prop = new Properties();

        try {
            prop.load(new FileReader("C:\\Users\\ibnel\\Documents\\rev\\w2\\JavaFundamentals\\src\\main\\resources\\application.properties"));
            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");

            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Established Connection to Database!");
        } catch (IOException e) {
            System.out.println("Property file not found!");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("Could not establish connection");
            throw new RuntimeException(e);
        }

        return conn;
    }

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("failed to load postgresql driver");
            throw new RuntimeException(e);
        }
    }

}
