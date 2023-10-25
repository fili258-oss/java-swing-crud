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
          //repository.delete(201);
            Actor fran = new Actor();
            fran.setactor_id(204);
            fran.setFirst_name("Francisco");
            fran.setLast_name("Rojas");
            repository.save(fran);

          System.out.println(repository.getById(201));
          //repository.findAll().forEach(System.out::println);
        } 
        catch (Exception e) {
            e.printStackTrace();   
            System.out.println("Error conexion");

        }
       
       
    }

}