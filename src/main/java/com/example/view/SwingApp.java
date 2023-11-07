package com.example.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.jar.JarEntry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        setTitle("Gestión de actores Sakilla");
        //No redimensionable
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,600);
        /* setLocation(100, 200); */
        /* setBounds(525, 350, 1000, 700); */
        setLocationRelativeTo(null);

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

        initiComponents();
        ActorRepository = new ActorRepository();

        //refreshTable();

        addButton.addActionListener(e-> {
            try {
               System.out.println("Hola mundo");
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

    public void initiComponents(){
        JPanel panel = new JPanel();//Creación de un panel
        /* panel.setBackground(Color.gray); */
        this.getContentPane().add(panel);//Agregamos el panel a la ventana

        JLabel title = new JLabel("Administración de actores");
        panel.add(title);
    }

    
}
