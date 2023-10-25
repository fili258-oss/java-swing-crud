package com.example.view;

import java.awt.BorderLayout;
import java.util.jar.JarEntry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.example.model.Actor;
import com.example.repository.ActorRepository;
import com.example.repository.Repository;
import com.mysql.cj.xdevapi.JsonArray;

public class SwingApp extends JFrame{
    private final Repository<Actor> ActorRepository;
    private final JTable ActorTable;

    public SwingApp(){
        setTitle("Gestion Sakila");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,300);

        ActorTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(ActorTable);
        add(scrollPane,BorderLayout.CENTER);

        JButton addButton = new JButton("Agregar");
        JButton updateButton = new JButton("Actualizar");
        JButton deleteButton = new JButton("Borrar");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel,BorderLayout.SOUTH);

        ActorRepository = new ActorRepository();

        //refreshTable();

        addButton.addActionListener(e-> {
            try {
               // addActor();
            } catch (Exception ex) {
                ex.printStackTrace(); 
            }
        });

        updateButton.addActionListener(e-> {
            try {
                //updateActor();
            } catch (Exception ex) {
                ex.printStackTrace(); 
            }
        });

        deleteButton.addActionListener(e-> {
            try {
                //deleteActor();
            } catch (Exception ex) {
                ex.printStackTrace(); 
            }
        });

    }
}
