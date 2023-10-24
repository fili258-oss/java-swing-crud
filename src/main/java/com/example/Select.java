package com.example;

import java.sql.Connection;
import java.sql.SQLException;

import com.example.model.Actor;
import com.example.repository.ActorRepository;
import com.example.repository.Repository;
import com.example.utils.DatabaseConnection;

public class Select {
    public static void main(String[] args) throws SQLException {

       try (Connection myConnection = DatabaseConnection.getInstance()){
           
          Repository<Actor> repository = new ActorRepository();
          repository.findAll().forEach(System.out::println);
        } 
        catch (Exception e) {
            e.printStackTrace();   
            System.out.println("Error conexion");

        }
       
       
    }

}