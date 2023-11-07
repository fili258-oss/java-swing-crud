package com.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static String url = "jdbc:mysql://localhost:3306";
    private static String user = "root";
    private static String pass = "12345678";

    private static Connection myConnection;

        public static Connection getInstance() throws SQLException{
            if(myConnection == null){
                myConnection = DriverManager.getConnection(url,user,pass);
            }
            return myConnection;
        }
    }
        