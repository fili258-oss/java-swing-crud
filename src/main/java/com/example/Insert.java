package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Insert {
    
    public static void main(String[] args) {
       Connection myConnection = null;
       PreparedStatement myStatement = null;
       try {
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
            String sql = "insert into sakila.actor(actor_id,first_name,last_name) values (?, ?,?)";
            myStatement = myConnection.prepareStatement(sql);
            myStatement.setString(1, "999");
            myStatement.setString(2, "Pacho");
            myStatement.setString(3, "Rojas");

            int rowsAffected = myStatement.executeUpdate();
            if (rowsAffected > 0){
                System.out.println("se afecto colomnas");
            }
           System.out.println("conexion exitosa");
        } 
        catch (Exception e) {
            e.printStackTrace();   
            System.out.println("Error conexion");

        }
    }
}