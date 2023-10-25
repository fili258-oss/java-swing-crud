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

    private Connection getConnection() throws Exception {
        return DatabaseConnection.getInstance();
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (PreparedStatement myStat = getConnection()
                .prepareStatement("Delete from sakila.actor where actor_id = ?")) {
            myStat.setInt(1, id);
            myStat.executeUpdate();
        }
    }

    @Override
    public List<Actor> findAll() throws Exception {

        List<Actor> actors = new ArrayList<>();
        try (Statement myStatement = getConnection().createStatement();
                ResultSet myResultSet = myStatement.executeQuery("select * from sakila.actor")) {
            while (myResultSet.next()) {
                Actor actor = createActor(myResultSet);
                actors.add(actor);
            }
        }
        return actors;
    }

    @Override
    public Actor getById(Integer id) throws Exception {
        Actor actor = null;
        try (PreparedStatement myStat = getConnection()
                .prepareStatement("Select * from sakila.actor where actor_id = ?")) {
            myStat.setInt(1, id);
            try (ResultSet myResult = myStat.executeQuery()) {
                if (myResult.next()) {
                    actor = createActor(myResult);
                }
            }
        }
        return actor;
    }

    @Override
    public void save(Actor actor) throws Exception {
        String sql;
        Actor existeId = getById(actor.getactor_id());
        if (existeId !=  null) {
            sql = "UPDATE sakila.actor SET first_name = ?, last_name= ? WHERE actor_id = ?";
        } else {
            sql = "INSERT INTO sakila.actor (first_name,last_name) values (?,?)";
        }
        try (PreparedStatement myStat = getConnection().prepareStatement(sql)) {
            myStat.setString(1, actor.getFirst_name());
            myStat.setString(2, actor.getLast_name());
            if (existeId != null) {
                myStat.setInt(3, actor.getactor_id());
            }

            myStat.executeUpdate();
        }
    }

    private Actor createActor(ResultSet myResultSet) throws SQLException {
        Actor actor = new Actor();
        actor.setactor_id(myResultSet.getInt("actor_id"));
        actor.setFirst_name(myResultSet.getString("first_name"));
        actor.setLast_name(myResultSet.getString("last_name"));
        return actor;
    }

}
