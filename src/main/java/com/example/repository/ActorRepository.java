package com.example.repository;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Actor;
import com.example.utils.DatabaseConnection;

public class ActorRepository implements Repository<Actor> {


    private Connection getConnection() throws Exception{
        return DatabaseConnection.getInstance();
    }
    @Override
    public void delete() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Actor> findAll() throws Exception {
       
        List<Actor> actors= new ArrayList<>();
        try(Statement myStatement = getConnection().createStatement();
       ResultSet myResultSet = myStatement.executeQuery("select * from sakila.actor")){
        while (myResultSet.next()) {
            Actor actor = createActor(myResultSet);
            actors.add(actor);
         }
       }
        return actors;
    }

   

    @Override
    public Actor getById(Integer id) throws Exception {
        
    }

    @Override
    public void save(Actor actor) throws Exception {
        
    }

     private Actor createActor(ResultSet myResultSet) throws SQLException {
        Actor actor = new Actor();
        actor.setactor_id(myResultSet.getInt("actor_id"));
        actor.setFirst_name(myResultSet.getString("first_name"));
        actor.setLast_name(myResultSet.getString("last_name"));
        return actor;
    }
    
}
