package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Update {
    public static void main(String[] args) {
       Connection myConnection = null;
       Statement myStatement = null;
       ResultSet myResult = null;
       try {
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
            myStatement = myConnection.createStatement();
            int rowsAffected = myStatement.executeUpdate("Update sakila.actor set first_name = 'Francisco' Where actor_id = 999");
            myResult = myStatement.executeQuery("select * from sakila.actor where actor_id = 999");
            while(myResult.next()){
                 System.out.println(myResult.getString("first_name"));
            }
            System.out.println("conexion exitosa");
        } 
        catch (Exception e) {
            e.printStackTrace();   
            System.out.println("Error conexion");
        }
    }
}